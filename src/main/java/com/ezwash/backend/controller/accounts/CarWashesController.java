package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarWashesController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create CarWashes", description = "Create and return a Car Wash", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWash created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/carwashes")
    public CarWashResource createCarWash(@Valid @RequestBody SaveCarWashResource resource) throws ParseException {
       Location location = locationService.getLocationById(resource.getLocation_id());
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM");
       Date birth_date = simpleDateFormat.parse(resource.getBirth_date()) ;
       CarWash carWash = convertToEntity(resource);
       carWash.setBirth_date(birth_date);
       return convertToResource(carWashService.createCarWash(carWash, location));
    }

    @Operation(summary = "Get near Car Washes", description = "Get all Car Washes within a radius in kilometers", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWashes founded", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/{lattitude}/{longitude}/{distance}")
    public Page<CarWashResource> getNearCarWashes(@PathVariable double lattitude, @PathVariable double longitude, @PathVariable double distance, Pageable pageable){
        List<CarWashResource> resources = carWashService.getCarWashesLessThanDistance(lattitude, longitude, distance, pageable)
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Edit Car Washes", description = "The Car Wash's owner can edit the Car Wash's data", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWash updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("carwashes/{carwashId}")
    public CarWashResource updateCarWash(@PathVariable Long carwashId, @RequestBody SaveCarWashResource resource){
        CarWash carWash = convertToEntity(resource);
        Location location = locationService.getLocationById(resource.getLocation_id());
        CarWashResource carWashResource = convertToResource(carWashService.editCarWash(carwashId, carWash, location));
        return carWashResource;
    }


    @Operation(summary = "Get CarWashes", description = "Get a Car Wash", tags = {"Car Washes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWash got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/{carwashId}")
    public CarWashResource getCarWashById(@PathVariable Long carwashId){
        return convertToResource(carWashService.findCarWashById(carwashId));
    }

    @Operation(summary = "Get CarWashes by Qualification", description = "Get a Car Wash by Qualification", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWashes By Qualification got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/qualification/{qualification}")
    public Page<CarWashResource> findByQualification(@PathVariable(value="qualification") Integer qualification,Pageable pageable){
        Page<CarWash> carWashPage = carWashService.findByQualification(qualification,pageable);
        List<CarWashResource> carWashResource = carWashPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(carWashResource,pageable,carWashResource.size());
    }


    @Operation(summary = "Get CarWashes by Range Qualification", description = "Get a Car Wash by Range Qualification", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWashes By Range of Qualification got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/qualification/{qualification1}/{qualification2}")
    public Page<CarWashResource> findByQualificationRange(@PathVariable(value="qualification1") Integer qualification,@PathVariable(value="qualification2") Integer qualification2,
                                                     Pageable pageable){
        Page<CarWash> carWashPage = carWashService.findByQualificationRange(qualification,qualification2,pageable);
        List<CarWashResource> carWashResource = carWashPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(carWashResource,pageable,carWashResource.size());
    }


    @Operation(summary = "Get CarWashes by Name", description = "Get all Car Wash that their Names contains some word", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWashes By Name got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/name/{name}")
    public Page<CarWashResource> getCarWashByName(@PathVariable(value="name") String name, Pageable pageable){
        Page<CarWash> carWashPage = carWashService.getCarWashByName(name, pageable);
        List<CarWashResource> carWashResource = carWashPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(carWashResource,pageable,carWashResource.size());
    }

    private CarWash convertToEntity(SaveCarWashResource resource){
        return mapper.map(resource, CarWash.class);
    }

    private CarWashResource convertToResource(CarWash carWash){
        return mapper.map(carWash, CarWashResource.class);
    }

}