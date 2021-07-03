package com.ezwash.backend.controller.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.business.ServiceService;
import com.ezwash.backend.resource.accounts.StaffResource;
import com.ezwash.backend.resource.business.SaveServiceResource;
import com.ezwash.backend.resource.business.ServiceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServicesController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create a Car Wash's service", description = "Create service given the Car Wash ID", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/carwashes/{carwashId}/services")
    public ServiceResource createServiceCarWash(@Valid @RequestBody SaveServiceResource resource, @PathVariable Long carwashId){
        CarWash carWash = carWashService.findCarWashById(carwashId);
        Service service = convertToEntity(resource);
        service.setCarWash(carWash);
        return convertToResource(serviceService.createService(service));
    }

    @Operation(summary = "Get a Car Wash's service", description = "Get service given the Car Wash ID", tags = {"Services"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services get successfully", content = @Content(mediaType = "application/json"))
    })  
    @GetMapping("/services/{serviceId}")
    public ServiceResource getServiceById(@PathVariable Long serviceId) {
        return convertToResource(serviceService.getServiceById(serviceId));
    }

    @Operation(summary = "Update a Car Wash's service", description = "Update a service given the Car Wash ID and the Service ID", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/carwashes/{carwashId}/services/{serviceId}")
    public ServiceResource updateService(
            @PathVariable Long carwashId,
            @PathVariable Long serviceId,
            @Valid @RequestBody SaveServiceResource resource){
        return convertToResource(serviceService.updateService(carwashId,serviceId,convertToEntity(resource)));
    }

    @Operation(summary = "Get a Car Wash's service", description = "Get a service given the Car Wash ID", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service obtained successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/{carWashId}/services")
    public List<Service> getServiceByCarWashId(@PathVariable Long carWashId){
        return serviceService.getServiceByCarWashId(carWashId);
    }

    @Operation(summary = "Delete Service", description = "Delete Service's CarWash", tags = {"Service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service's carwash deleted successfully", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/carwashes/{idService}/services")
    public ResponseEntity<?> deleteStaffId (@PathVariable Long idService){
        return serviceService.deleteServiceById(idService);
    }

    private Service convertToEntity(SaveServiceResource resource){
        return mapper.map(resource, Service.class);
    }

    private ServiceResource convertToResource(Service service){
        return mapper.map(service, ServiceResource.class);
    }
}
