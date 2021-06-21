package com.ezwash.backend.service.interactions;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.repository.interactions.VehicleRepository;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Vehicle createVehicle (Vehicle vehicle,Location location){
        vehicle.setLocation_id(location);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Location getLocationById (Long id){

        return locationRepository.findById(id).get();
    }

    @Override
    public Customer getUserById(Long id){
        return customerRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<?> deleteCarById (Long id_car){
        Vehicle vehicle = vehicleRepository.findById(id_car).orElseThrow(()-> new ResourceNotFoundException(
                "Vehicle","Id", id_car));
        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Vehicle> getVehiclesByCustomerId(Long customerId){
       Customer customer = customerRepository.findById(customerId)
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));

       return vehicleRepository.findVehicleByCustomerId(customerId);
    }
}
