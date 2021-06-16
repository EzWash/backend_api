package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;

import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;

import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarWashRepository carWashRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Customer createCustomer(Customer customer, Location location) {
        customer.setLocation(location);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public Customer addCustomerCarwash(Long userId, Long carWashId) {
        CarWash carWash = carWashRepository.findById(carWashId)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", carWashId));
        return customerRepository.findById(userId).map(
                user -> customerRepository.save(user.addCarWashToLikedList(carWash)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Customer deleteCustomerCarWash(Long userId, Long carWashId) {
        CarWash carWash = carWashRepository.findById(carWashId)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", carWashId));
        return customerRepository.findById(userId).map(
                user -> customerRepository.save(user.deleteCarWashFromLikedList(carWash)))
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public Customer updateCustomer(Long userId, Customer customerRequest) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        customer.setFirst_name(customerRequest.getFirst_name())
                .setLast_name(customerRequest.getLast_name())
                .setPhone_number(customerRequest.getPhone_number())
                .setEmail(customerRequest.getEmail())
                .setGender(customerRequest.getGender());
        return customerRepository.save(customer);
    }

    @Override
    public Page<CarWash> getLikedList(Long userId, Pageable pageable) {
        List<CarWash> carWashes = findCustomerById(userId).getLikedCarwashes();
        return new PageImpl<>(carWashes, pageable, carWashes.size());

    }

    @Override
    public Page<Contract> getContractList(Long userId, Pageable pageable) {
        List<Contract>contracts= findCustomerById(userId).getContractList();
        return new PageImpl<>(contracts,pageable,contracts.size());
    }

}