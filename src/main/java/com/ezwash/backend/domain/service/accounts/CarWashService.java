package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarWashService {
    CarWash createCarWash(CarWash carWash, Location location);
    CarWash findCarWashById(Long id);
    Page<CarWash> getCarWashesLessThanDistance(double lt_1, double lg_1, double distance, Pageable pageable);
}
