package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    @Query("select s from Staff s where carwash_id= :id")
    public List<Staff> listStaffByCarWashId (@Param("id") Long id);
}
