package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer, Location location);
    Customer findCustomerById(Long id);
    Customer addCustomerCarwash(Long customerId, Long carWashId);
    Customer deleteCustomerCarWash(Long customerId, Long carWashId);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    List<CarWash> getLikedList(Long customerId);
    Page<Contract> getContractList(Long customerId, Pageable pageable);
}
