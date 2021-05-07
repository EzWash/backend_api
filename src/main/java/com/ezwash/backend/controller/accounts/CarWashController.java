package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public CarWashResource createCarWash(@Valid @RequestBody SaveCarWashResource resource){
       Location location = locationService.getLocationById(resource.getLocation()) ;
       CarWash carWash = convertToEntity(resource);
       return convertToResource(carWashService.createCarWash(carWash, location));
    }

    @GetMapping("/carwashes/{lattitude}/{longitude}/{distance}")
    public Page<CarWashResource> getNearCarWashes(@PathVariable double lattitude, @PathVariable double longitude, @PathVariable double distance, Pageable pageable){
        List<CarWashResource> resources = carWashService.getCarWashesLessThanDistance(lattitude, longitude, distance, pageable)
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    private CarWash convertToEntity(SaveCarWashResource resource){
        return mapper.map(resource, CarWash.class);
    }

    private CarWashResource convertToResource(CarWash carWash){
        return mapper.map(carWash, CarWashResource.class);
    }
}
