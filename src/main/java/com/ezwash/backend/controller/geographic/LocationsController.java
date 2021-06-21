package com.ezwash.backend.controller.geographic;

import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.geographic.LocationResource;
import com.ezwash.backend.resource.geographic.SaveLocationResource;
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

   @PostMapping("/locations")
   public LocationResource createLocation(
           @RequestBody SaveLocationResource resource
   ){
      Location location = convertToEntity(resource);
      return convertToEntity(locationService.createLocation(location));
   }

   private Location convertToEntity(SaveLocationResource resource){return mapper.map(resource, Location.class);}
   private LocationResource convertToEntity(Location location){return mapper.map(location, LocationResource.class);}
}