package com.ezwash.backend.domain.service.accounts;


import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    Staff createStaff(CarWash carWash, Staff staff);
    Staff updateStaff(Long carWashId, Long staffId, Staff staff);
    List<Staff> getStaffByCarWashId(Long carWashId);
    ResponseEntity<?> deleteStaffById (Long idStaff);
}
