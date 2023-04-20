package com.example.sakilaapi.controller.api;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.example.dto.staff.StaffDto;
import org.example.dto.staff.StaffSummaryDto;
import org.example.mapper.staff.StaffMapper;
import org.example.repository.StaffRepository;
import org.example.service.StaffService;

import java.util.List;
import java.util.Optional;

@WebService(name = "StaffService")
public class StaffController {

    private final StaffService service = new StaffService(
            new StaffRepository(), StaffMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllStaff")
    @WebResult(name = "staffs")
    public List<StaffSummaryDto> getAllStaff() {
        return service.getStaffSummaries();
    }

    @WebMethod(operationName = "getStaffById")
    @WebResult(name = "staff")
    public StaffSummaryDto getStaffById(@WebParam(name = "id") Integer id) {
        Optional<StaffSummaryDto> optionalStaff = Optional.ofNullable(service.getStoreSummaryById(id));
        if (optionalStaff.isPresent()) {
            return optionalStaff.get();
        } else {
            throw new WebServiceException("Staff with id " + id + " not found");
        }
    }

    @WebMethod(operationName = "createStaff")
    @WebResult(name = "staffAdded")
    public StaffDto createStaff(@WebParam(name = "staff") StaffDto staffDto) {
        Optional<StaffDto> optionalStaffDto = Optional.ofNullable(service.create(staffDto, staffDto.getId()));
        if (optionalStaffDto.isPresent()) {
            return optionalStaffDto.get();
        } else {
            throw new WebServiceException("Can't create staff member");
        }
    }

    @WebMethod(operationName = "updateStaff")
    @WebResult(name = "staffUpdated")
    public String updateStaff(@WebParam(name = "id") Integer id, @WebParam(name = "staff") StaffDto staffDto) {
        staffDto.setId(id);
        boolean res = service.update(id, staffDto);
        if (res) {
            return "Staff was updated successfully";
        } else {
            throw new WebServiceException("Can't update staff member");
        }
    }

    @WebMethod(operationName = "deleteStaffById")
    @WebResult(name = "staffDeleted")
    public String deleteStaffById(@WebParam(name = "id") Integer id) {
        service.deleteById(id);
        return "Staff was deleted successfully";
    }
}
