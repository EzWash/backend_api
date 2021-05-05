package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
