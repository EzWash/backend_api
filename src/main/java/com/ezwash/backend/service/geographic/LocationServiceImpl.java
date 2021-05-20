package com.ezwash.backend.service.geographic;

import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location getLocationById(Long Id) {
       return locationRepository.findById(Id)
               .orElseThrow(() -> new ResourceNotFoundException("Location", "Id", Id));
    }
}
