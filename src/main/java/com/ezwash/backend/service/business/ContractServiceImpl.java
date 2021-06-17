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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class ContractServiceImpl implements ContractService {


    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ContractRepository contractRepository;



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
        staff.getContractList().add(contract);
        return contractRepository.save(contract);

    }

    @Override
    public Page<Contract> getContractsByState(String state, Pageable pageable){
       List<Contract>  contractList = contractRepository.findContractByStateEquals(state);
       if (!(state.equals("pending")) && !(state.equals("active")) && !(state.equals("washing")) && !(state.equals("readytogo")) && !(state.equals("finished")) )
           throw new ResourceNotFoundException("State", "Invalid", state);
       if (contractList.size() == 0){
           throw new ResourceNotFoundException("Contracts", "Found", 0);
       } else{
           return new PageImpl<>(contractList, pageable,  contractList.size());
       }
    }

}
