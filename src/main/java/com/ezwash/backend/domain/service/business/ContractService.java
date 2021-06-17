package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Contract;

public interface ContractService {
    Contract findContractById(Long id);
    Contract designateStaffToContract(Long contractId, Long staffId);
}
