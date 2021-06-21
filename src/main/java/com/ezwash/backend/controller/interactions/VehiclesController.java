package com.ezwash.backend.controller.interactions;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import com.ezwash.backend.resource.interactions.SaveVehicleResource;
import com.ezwash.backend.resource.interactions.VehicleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VehiclesController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create a Vehicle", description = "Create the User's Vehicle given an ID", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Posts returned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping ("customers/{customer_id}/vehicles")
    public VehicleResource createVehicle (@Valid @RequestBody SaveVehicleResource resource,@PathVariable Long customer_id){
        Customer customer = customerService.findCustomerById(customer_id);
        Vehicle vehicle = convertToEntity(resource);
        vehicle.setUser(customer);
        Location location = locationService.getLocationById(resource.getLocation_id());
        return convertToResource(vehicleService.createVehicle(vehicle,location));
    }

    @Operation(summary = "Delete Vehicle", description = "Delete Vehicle's User", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle's user deleted successfully", content = @Content(mediaType = "application/json"))
    })

    @DeleteMapping("/vehicles/{idCar}/customers")
    public ResponseEntity<?> deleteCarById (@PathVariable Long idCar){
        return vehicleService.deleteCarById(idCar);
    }

    @Operation(summary = "Get Vehicles by Customer Id", description = "Get all Vehicles given Customer Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/vehicles/list")
    public List<VehicleResource> getContractsByState(@PathVariable Long customerId){
        List<Vehicle> vehicleList = vehicleService.getVehiclesByCustomerId(customerId);
        List<VehicleResource> vehicleResourceList = vehicleList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return vehicleResourceList;
    }

    private Vehicle convertToEntity(SaveVehicleResource resource){
        return mapper.map(resource,Vehicle.class);
    }
    private VehicleResource convertToResource (Vehicle vehicle){
        return mapper.map(vehicle,VehicleResource.class);
    }

}
