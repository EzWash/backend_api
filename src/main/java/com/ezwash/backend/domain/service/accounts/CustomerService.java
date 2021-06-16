package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Customer createCustomer(Customer customer, Location location);
    Customer findCustomerById(Long id);
    Customer addCustomerCarwash(Long customerId, Long carWashId);
    Customer deleteCustomerCarWash(Long customerId, Long carWashId);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    Page<CarWash> getLikedList(Long customerId, Pageable pageable);
    Page<Contract> getContractList(Long customerId, Pageable pageable);
}
