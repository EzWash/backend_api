package com.ezwash.backend.service.interactions;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.repository.interactions.VehicleRepository;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Vehicle createVehicle (Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Location getLocationById (Long id){

        return locationRepository.findById(id).get();
    }

    @Override
    public User  getUserById( Long id){
        return userRepository.findById(id).get();
    }



}
