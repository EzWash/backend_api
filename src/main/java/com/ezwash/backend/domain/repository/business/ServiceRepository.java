package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
