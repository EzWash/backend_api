package com.ezwash.backend.domain.service.accounts;


import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService {
    Staff createStaff(CarWash carWash, Staff staff);
    Staff updateStaff(Long carWashId, Long staffId, Staff staff);
    Page<Staff> getStaffByCarWashId(Long carWashId, Pageable pageable);
}
