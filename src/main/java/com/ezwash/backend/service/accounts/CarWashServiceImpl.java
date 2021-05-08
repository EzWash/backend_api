package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import com.ezwash.backend.resource.business.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public CarWash editCarWash(Long carwashId, CarWash carWashRequest){
        CarWash carwash = carWashRepository.findById(carwashId)
                .orElseThrow(() -> new ResourceNotFoundException("CarWash", "Id", carwashId));
        carwash.setDescription(carWashRequest.getDescription())
                .setName(carWashRequest.getName())
                .setName_owner(carWashRequest.getName_owner())
                .setLocation(carWashRequest.getLocation());

        return carWashRepository.save(carwash);
    }

    public CarWash findCarWashById(Long id) {
        return carWashRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", id));
    }

    @Override
    public Page<CarWash> getCarWashesLessThanDistance(double lt_1, double lg_1, double distance, Pageable pageable) {
        List<CarWash> carWashesNear = new ArrayList<>();
        Page<CarWash> carWashes = carWashRepository.findAll(pageable)
                .map(carWash -> {
                    double diff_distance = carWash.getLocation().getDistance(lt_1, lg_1);
                    if(diff_distance <= distance) {
                        carWashesNear.add(carWash);
                    }
                    return carWash;
                });
        if(carWashesNear.size() == 0){
            throw new ResourceNotFoundException("CarWash", "Distance", distance);
        }
        return new PageImpl<>(carWashesNear, pageable, carWashesNear.size());
    }
}