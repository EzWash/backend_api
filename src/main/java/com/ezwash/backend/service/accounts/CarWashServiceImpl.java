package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarWashServiceImpl implements CarWashService {
    @Autowired
    private CarWashRepository carWashRepository;

    @Override
    public CarWash createCarWash(CarWash carWash, Location location) {
        carWash.setLocation(location);
        carWash.setAvailable(1);
        carWash.setQualification(0);
        return carWashRepository.save(carWash);
    }

    @Override
    public CarWash findCarWashById(Long id) {
        return carWashRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", id));
    }
}
