package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.ContractService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Contract findContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", id));
    }

    @Override
    public Contract designateStaffToContract(Long contractId, Long staffId){

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", contractId));

        contract.setStaff(staff);

        return contractRepository.save(contract);

    }

}
