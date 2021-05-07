package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.stereotype.Service;

public interface CarWashService {
    CarWash createCarWash(CarWash carWash, Location location);
    CarWash editCarWash(Long carwashId, CarWash carWashRequest);
    CarWash findCarWashById(Long id);
}