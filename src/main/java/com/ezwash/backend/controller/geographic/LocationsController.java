package com.ezwash.backend.controller.geographic;

import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.geographic.LocationResource;
import com.ezwash.backend.resource.geographic.SaveLocationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationsController {
   @Autowired
   private ModelMapper mapper;

   @Autowired
   private LocationService locationService;

   @Operation(summary = "Create a Location based on Address", description = "Create a location based on address, latitude and longitude", tags = {"Security"})
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Locations created successfully", content = @Content(mediaType = "application/json"))
   })
   @PostMapping("/auth/locations")
   public LocationResource createLocation(
           @RequestBody SaveLocationResource resource
   ){
      Location location = convertToEntity(resource);
      return convertToResource(locationService.createLocation(location));
   }

   @Operation(summary = "Get Location by Customer", description = "Get a location based on the Customer", tags = {"Location"})
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Locations returned successfully", content = @Content(mediaType = "application/json"))
   })
   @GetMapping("/customers/{customerId}/locations")
   public LocationResource getLocationByCustomer(@PathVariable Long customerId){
      return convertToResource(locationService.getLocationByUser(customerId));
   }

   private Location convertToEntity(SaveLocationResource resource){return mapper.map(resource, Location.class);}
   private LocationResource convertToResource(Location location){return mapper.map(location, LocationResource.class);}
}