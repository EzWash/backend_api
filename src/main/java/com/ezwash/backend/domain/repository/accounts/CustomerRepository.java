package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findCustomerByEmail(String Email);
}
