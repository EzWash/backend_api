package com.ezwash.backend.controller.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.business.ServiceService;
import com.ezwash.backend.resource.business.SaveServiceResource;
import com.ezwash.backend.resource.business.ServiceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/carwash/{carwashId}/service")
    public ServiceResource createServiceCarWash(@Valid @RequestBody SaveServiceResource resource, @PathVariable Long carwashId){
        CarWash carWash = carWashService.findCarWashById(carwashId);
        Service service = convertToEntity(resource);
        service.setCarWash(carWash);
        return convertToResource(serviceService.createService(service));
    }
    private Service convertToEntity(SaveServiceResource resource){
        return mapper.map(resource, Service.class);
    }

    private ServiceResource convertToResource(Service service){
        return mapper.map(service, ServiceResource.class);
    }
}
