package com.ezwash.backend.controller.interactions;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import com.ezwash.backend.resource.interactions.SaveVehicleResource;
import com.ezwash.backend.resource.interactions.VehicleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class VehiclesController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create a Vehicle", description = "Create the User's Vehicle given an ID", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Posts returned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping ("/vehicles")
    public VehicleResource createVehicle (@Valid @RequestBody SaveVehicleResource resource){
        Location location = vehicleService.getLocationById(resource.getLocation());
        Customer customer = vehicleService.getUserById(resource.getUser());
        Vehicle vehicle= convertToEntity(resource);
        vehicle.setLocation(location);
        vehicle.setUser(customer);
        VehicleResource vehicleResource = convertToResource(vehicleService.createVehicle(vehicle))
                .setCustomer_Id(resource.getUser());
        return vehicleResource;
    }

    private Vehicle convertToEntity(SaveVehicleResource resource){
        return mapper.map(resource,Vehicle.class);
    }
    private VehicleResource convertToResource (Vehicle vehicle){
        return mapper.map(vehicle,VehicleResource.class);
    }

}
