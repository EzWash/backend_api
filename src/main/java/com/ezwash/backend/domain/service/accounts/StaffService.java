package com.ezwash.backend.domain.service.accounts;


import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;

public interface StaffService {
    Staff createStaff(Staff staff, CarWash carwashId);
    Staff editStaff();
    Staff findStaffById(Long id);

}
