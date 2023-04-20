package org.example.repository;

import org.example.dto.staff.StaffSummaryDto;
import org.example.mapper.staff.StaffSummaryMapper;
import org.example.model.Staff;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class StaffRepository extends BaseRepository<Staff,Integer> {
    StaffSummaryMapper staffSummaryMapper;
    public StaffRepository() {
        super(Staff.class);
        this.staffSummaryMapper = StaffSummaryMapper.INSTANCE;
    }

    public List<StaffSummaryDto> getStaffSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Staff> stores = getAll(entityManager);
                    return staffSummaryMapper.toDto(stores);
                }
        );
    }

    public StaffSummaryDto getStoreSummaryById(Integer id) {
        return Database.doInTransaction(
                entityManager -> {
                    Staff staff = entityManager.find(Staff.class, id);
                    if (staff == null)
                        throw new EntityNotFoundException("Staff can't be fetched with id: "+id);
                    return staffSummaryMapper.toDto(staff);
                }
        );
    }
}
