package com.ezwash.backend.service.geographic;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Location getLocationById(Long Id) {
       return locationRepository.findById(Id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "Id", Id));
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location getLocationByUser(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
        return customer.getLocation();
    }
}
