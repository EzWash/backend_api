package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
