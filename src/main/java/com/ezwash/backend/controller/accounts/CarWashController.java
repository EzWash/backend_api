package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarWashController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/carwash")
    public CarWashResource createCarWash(@RequestBody SaveCarWashResource resource){
       Location location = locationService.getLocationById(resource.getLocation()) ;
       CarWash carWash = convertToEntity(resource);
       return convertToResource(carWashService.createCarWash(carWash, location));
    }

    @PutMapping("carwash/{carwashId}")
    public CarWashResource updateCarWash(@PathVariable Long carwashId, @RequestBody SaveCarWashResource resource){
        CarWash carWash = convertToEntity(resource);
        Location location = locationService.getLocationById(resource.getLocation()) ;
        return convertToResource(carWashService.editCarWash(carwashId, carWash,location));
    }

    private CarWash convertToEntity(SaveCarWashResource resource){
        return mapper.map(resource, CarWash.class);
    }

    private CarWashResource convertToResource(CarWash carWash){
        return mapper.map(carWash, CarWashResource.class);
    }
}
