package org.example.service.rental;

import org.example.controller.request.FilmRentalRequest;
import org.example.dto.rental.RentalDto;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDto> getAllRentals();
    Optional<RentalDto> getRentalById(Integer id);
    RentalDto createRental(FilmRentalRequest filmRentalRequest);
    RentalDto updateRental(RentalDto rental);
    void deleteRentalById(Integer id);
}

