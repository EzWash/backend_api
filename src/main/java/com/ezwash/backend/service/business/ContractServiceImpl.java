package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.repository.business.CartRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarWashRepository carWashRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CartRepository cartRepository;




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
    public List<Contract> getContractsByState(String state, Long customerId){
        if (!(state.equals("pending")) && !(state.equals("active")) && !(state.equals("washing")) && !(state.equals("readytogo")) && !(state.equals("finished")) )
            throw new ResourceNotFoundException("State", "Invalid", state);
        if (!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        List<Contract>  contractList = contractRepository.findContractByStateEqualsAndCustomerId(state, customerId);
        return contractList;
    }

    @Override
    public List<Contract> getContractsByStateNot(String state, Long customerId){
        if (!(state.equals("pending")) && !(state.equals("active")) && !(state.equals("washing")) && !(state.equals("readytogo")) && !(state.equals("finished")) )
            throw new ResourceNotFoundException("State", "Invalid", state);
        if (!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        List<Contract>  contractList = contractRepository.findContractByStateNotAndCustomerId(state, customerId);
        return contractList;
    }

    @Override
    public Contract updateContractState(Long contractId, String state){
       if (!(state.equals("active")) && !(state.equals("washing")) && !(state.equals("readytogo")) && !(state.equals("finished")) )
           throw new ResourceNotFoundException("State", "Invalid", state);
       Contract contract = contractRepository.findById(contractId)
               .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", contractId));

       if (contract.getState().equals("finished"))
           throw new ResourceNotFoundException("Contract", "State", contract.getState());

       contract.setState(state);

       return contractRepository.save(contract);

    }

    @Override
    public Contract createContract(Long carWashId, Long customerId){
        Customer customer = customerRepository.findById(customerId)
              .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));

        CarWash carWash = carWashRepository.findById(carWashId)
              .orElseThrow(() -> new ResourceNotFoundException("CarWash", "Id", carWashId));

        Contract contract = new Contract();

        contract.setCustomer(customer);
        contract.setCarWash(carWash);
        contract.setState("pending");
        return contractRepository.save(contract);
    }

    @Override
    public List<Service> getServicesFromContract(Long contractId){
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", contractId));
        return contract.getServiceContracts();
    }

    @Override
    public Contract setListServices(Long contractId, Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
        List<Service> serviceList = customer.getCart().getServiceList();

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", contractId));

        for(Integer i = 0; i < serviceList.size(); i++){
            contractRepository.save(contract.addServiceToContract(serviceList.get(i)));
        }

        for(Integer i = 0; i < contract.getServiceContracts().size(); i++){
            cartRepository.save(customer.getCart().deleteServiceFromCart(contract.getServiceContracts().get(i)));
            customerRepository.save(customer);
        }

        contract.updateTotal();

        return contractRepository.save(contract);
    }
}
