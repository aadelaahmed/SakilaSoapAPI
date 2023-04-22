package com.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.staff.StaffDto;
import org.example.dto.staff.StaffSummaryDto;
import org.example.mapper.staff.StaffMapper;
import org.example.model.Staff;
import org.example.repository.StaffRepository;
import org.example.service.StaffService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@WebService(name = "staff")
public class StaffController {

    private final StaffService service = new StaffService(
            new StaffRepository(), StaffMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllStaff")
    @WebResult(name = "staffs")
    public List<StaffSummaryDto> getAllStaff() {
        List<StaffSummaryDto> staffs = service.getStaffSummaries();
        return staffs;
    }

    @WebMethod(operationName = "getStaffById")
    @WebResult(name = "staff")
    public StaffSummaryDto getStaffById(@WebParam(name = "id") Integer id) {
        Optional<StaffSummaryDto> optionalStaff = Optional.ofNullable(service.getStoreSummaryById(id));
        if (optionalStaff.isPresent()) {
            return optionalStaff.get();
        }
        throw new WebServiceException("Staff with id " + id + " not found");
    }

    @WebMethod(operationName = "createStaff")
    @WebResult(name = "staffAdded")
    public StaffSummaryDto createStaff(@WebParam(name = "staff") StaffDto staffDto) {
        staffDto.setLastUpdate(Instant.now());
        Optional<StaffSummaryDto> optionalStaffDto = Optional.ofNullable(service.createStaffByEmail(staffDto));
        if (optionalStaffDto.isPresent()) {
            return optionalStaffDto.get();
        }
        throw new WebServiceException("Can't create staff member");
    }

    @WebMethod(operationName = "updateStaff")
    @WebResult(name = "staffUpdated")
    public StaffDto updateStaff(@WebParam(name = "id") Integer id, @WebParam(name = "staff") StaffDto staffDto) {
        staffDto.setId(id);
        StaffDto res = service.update(id, staffDto);
        if (res != null) {
            return res;
        } else {
            throw new WebServiceException("Can't update this staff");
        }
    }

    @WebMethod(operationName = "deleteStaffById")
    @WebResult(name = "staffDeleted")
    public ResponseMessage deleteStaffById(@WebParam(name = "id") Integer id) {
        service.deleteById(id);
        return new ResponseMessage("Staff was deleted successfully");
    }
}