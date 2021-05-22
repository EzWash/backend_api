package com.ezwash.backend.domain.service.accounts;


import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;

public interface StaffService {
    Staff createStaff(CarWash carWash, Staff staff);
    Staff updateStaff(Long carWashId, Long staffId, Staff staff);
}
