package org.example.repository;

import org.example.controller.request.FilmRentalRequest;
import org.example.dto.rental.RentalDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.rental.RentalMapper;
import org.example.mapper.rental.RentalSummaryMapper;
import org.example.model.*;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class RentalRepository extends BaseRepository<Rental, Integer> {
    RentalSummaryMapper summaryMapper;
    public RentalRepository(){
        super(Rental.class);
        this.summaryMapper = RentalSummaryMapper.INSTANCE;
    }


    public RentalSummaryDto save(FilmRentalRequest filmRentalRequest) {
        return Database.doInTransaction(entityManager -> {
            //firstly,ensures that there is inventory with this id in db.
            Integer filmId = filmRentalRequest.getFilmId();
            Film film =entityManager.find(Film.class,filmId);
            if (film == null)
                throw new EntityNotFoundException("Can't find film of id: " + filmId);
            //then,ensures that there is Customer with this id in db.
            Integer customerId = filmRentalRequest.getCustomerId();
            Customer customer = entityManager.find(Customer.class,customerId);
            if (customer == null)
                throw new EntityNotFoundException("Can't find customer of id: " + customerId);
            //then,ensures that there is Staff with this id in db.
            Integer staffId = filmRentalRequest.getStaffId();
            Staff staff = entityManager.find(Staff.class,staffId);
            if (staff == null)
                throw new EntityNotFoundException("Can't find staff of id: " + staffId);
            //create inventory object to set it in the rental.
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(customer.getStore());
            inventory.setLastUpdate(Instant.now());
            entityManager.persist(inventory);
            //now, create the rental object to be saved in db.
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setLastUpdate(Instant.now());
            rental.setStaff(staff);
            rental.setInventory(inventory);
            Instant rentalDate = Instant.now();
            rental.setRentalDate(rentalDate);
            Instant expectedReturnDate = getExpectedReturnDate(rentalDate);
            rental.setReturnDate(expectedReturnDate);
            rental = entityManager.merge(rental);
            return summaryMapper.toDto(rental);
            //return rentalMapper.toDto(rental);
        });
    }
    private Instant getExpectedReturnDate(Instant rentalDate) {
        return rentalDate.plus(Duration.ofDays(7));
    }

    public List<RentalSummaryDto> getRentalSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Rental> query = builder.createQuery(Rental.class);
                    Root<Rental> root = query.from(Rental.class);
                    query.select(root);
                    List<Rental> rentals = entityManager.createQuery(query).getResultList();
                    if (rentals.isEmpty())
                        throw new EntityNotFoundException("There are no rentals");
                    return summaryMapper.toDto(rentals);
                }
        );
    }

    public RentalSummaryDto getRentalSummaryById(Integer rentalId) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Rental> query = builder.createQuery(Rental.class);
                    Root<Rental> root = query.from(Rental.class);
                    query.select(root)
                            .where(builder.equal(root.get("id"), rentalId)); // Add predicate to filter by rentalId
                    List<Rental> rentals = entityManager.createQuery(query).getResultList();
                    if (rentals.isEmpty()) {
                        throw new EntityNotFoundException("There are no rentals with id: " + rentalId);
                    }
                    return summaryMapper.toDto(rentals.get(0));
                }
        );
    }
}
