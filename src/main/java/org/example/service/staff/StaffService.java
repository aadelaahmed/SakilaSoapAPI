package org.example.service.staff;

import org.example.dto.staff.StaffDto;
import org.example.dto.staff.StaffSummaryDto;
import org.example.dto.store.StoreDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.staff.StaffMapper;
import org.example.mapper.store.StoreMapper;
import org.example.model.Staff;
import org.example.model.Store;
import org.example.repository.BaseRepository;
import org.example.repository.StaffRepository;
import org.example.repository.StoreRepository;
import org.example.service.BaseService;

import java.util.List;

public class StaffService extends BaseService<Staff, StaffDto> {
    StaffRepository staffRepository;
    StaffMapper staffMapper;
    public StaffService(StaffRepository staffRepository, StaffMapper staffMapper) {
        super(staffRepository, staffMapper);
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    protected Class<Staff> getEntityClass() {
        return Staff.class;
    }

    @Override
    protected Class<StaffDto> getDtoClass() {
        return StaffDto.class;
    }


    public StaffSummaryDto getStoreSummaryById(Integer id){
        return staffRepository.getStoreSummaryById(id);
    }
    public List<StaffSummaryDto> getStaffSummaries() {
        return staffRepository.getStaffSummaries();
    }
    public StaffSummaryDto createStaffByEmail(StaffDto staffDto) {
        return staffRepository.createStaffByEmail(staffDto);
    }
}
