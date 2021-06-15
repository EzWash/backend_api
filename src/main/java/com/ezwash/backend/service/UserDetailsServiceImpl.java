package com.ezwash.backend.service;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username);
        String defaultPassword = passwordEncoder.encode(customer.getPassword());
        if(customer.getEmail().equals(username)) {
            return new User(customer.getEmail(), defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with username " + username);
    }
}
