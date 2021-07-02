package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
    Contract findContractById(Long id);
    Contract designateStaffToContract(Long contractId, Long staffId);
    List<Contract> getContractsByState(String state, Long customerId);
    List<Contract> getContractsByStateNot(String state, Long customerId);
    Contract updateContractState(Long contractId, String state);
    Contract createContract(Long carWashId, Long customerId);
    Contract setListServices(Long contractId, Long customerId);
    List<Service> getServicesFromContract(Long contractId);
}
