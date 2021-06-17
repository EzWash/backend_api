package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findContractByStateEquals(String state);
}
