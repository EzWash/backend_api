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
    public CarWash findCarWashById(Long id) {
        return carWashRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", id));
    }

    @Override
    public Page<CarWash> getCarWashesLessThanDistance(User user, double distance, Pageable pageable) {
        double lt_user = user.getLocation().getLattitude();
        double lg_user = user.getLocation().getLongitude();
        List<CarWash> carWashesNear = new ArrayList<>();
        Page<CarWash> carWashes = carWashRepository.findAll(pageable)
                .map(carWash -> {
                    double lt_carwash = carWash.getLocation().getLattitude();
                    double lg_carwash = carWash.getLocation().getLongitude();
                    if(Distance.getDistance(lt_user, lg_user, lt_carwash, lg_carwash) <= distance) {
                        carWashesNear.add(carWash);
                    }
                    return carWash;
                });
        return new PageImpl<>(carWashesNear, pageable, carWashesNear.size());
    }
}