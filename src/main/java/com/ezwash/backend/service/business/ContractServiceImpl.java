package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractServiceImpl {
    @Autowired
    private ContractRepository contractRepository;

    public Contract findContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", id));
    }
}
