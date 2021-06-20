package com.ezwash.backend.domain.service.interactions;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    Vehicle createVehicle (Vehicle vehicle, Location location);
    Location getLocationById(Long id);
    Customer getUserById(Long id);
    ResponseEntity<?> deleteCarById (Long id_car);
    List<Vehicle> getVehiclesByCustomerId(Long customerId);
}
