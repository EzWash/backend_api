package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.service.accounts.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Override
    public Staff createStaff(Staff staff, CarWash carwash) {
        return null;
    }

    @Override
    public Staff editStaff() {
        return null;
    }

    @Override
    public Staff findStaffById(Long id) {
        return null;
    }


}
