package com.ezwash.backend.domain.service.interactions;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;

public interface VehicleService {

    Vehicle createVehicle (Vehicle vehicle);
    Location getLocationById(Long id);
    Customer getUserById(Long id);

}
