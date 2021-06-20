package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    @Query("select s from Service s where carwash_id= :id")
    public List<Service> listServiceByCarWashId (@Param("id") Long id);
}
