package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.request.FilmRentalRequest;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.rental.RentalMapper;
import org.example.repository.RentalRepository;
import org.example.service.rental.RentalService;
import org.example.service.rental.RentalServiceImpl;

import java.util.List;
import java.util.Optional;
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

@WebService(name = "RentalService")
public class RentalController {

    private final RentalService service = new RentalService(
            new RentalRepository(), RentalMapper.INSTANCE
    );

    @WebMethod(operationName = "createRental")
    @WebResult(name = "rentalSummary")
    public RentalSummaryDto createRental(@WebParam(name = "filmRentalRequest") FilmRentalRequest filmRentalRequest) {
        RentalSummaryDto rentalSummaryDto = service.createRental(filmRentalRequest);
        if (rentalSummaryDto != null){
            return rentalSummaryDto;
        }
        throw new WebServiceException("Can't create this rental object");
    }

    @WebMethod(operationName = "getAllRentals")
    @WebResult(name = "rentalSummaries")
    public List<RentalSummaryDto> getAllRentals() {
        List<RentalSummaryDto> summaries = service.getRentalSummaries();
        return summaries;
    }

    @WebMethod(operationName = "getRentalById")
    @WebResult(name = "rentalSummary")
    public RentalSummaryDto getRentalById(@WebParam(name = "id") Integer id) {
        RentalSummaryDto summary = service.getRentalSummaryById(id);
        if (summary == null) {
            throw new WebServiceException("Rental with id " + id + " not found");
        }
        return summary;
    }
}
