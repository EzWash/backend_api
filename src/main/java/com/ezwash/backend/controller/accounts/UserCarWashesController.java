package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserCarWashesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Add Car Wash to User's Liked List", description = "Add Car Wash to User's Liked list through the User Id and Car Wash Id", tags = {"User CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash added successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/carwashes/{carwashId}")
    public UserResource addUserCarWash(
            @PathVariable Long userId,
            @PathVariable Long carwashId){
        return convertToUserResource(userService.addUserCarwash(userId, carwashId));
    }

    @Operation(summary = "Get all Car Washes to User's liked list", description = "Get all Car Washes from the User's liked list through the User Id", tags = {"User CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash added successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/carwashes")
    public Page<CarWashResource> getLikedList(@PathVariable Long userId, Pageable pageable){
        Page<CarWash> carWashPage = userService.getLikedList(userId, pageable);
        List<CarWashResource> carWashResources = carWashPage.getContent()
                .stream()
                .map(this::convertToCarWashResource)
                .collect(Collectors.toList());
        return new PageImpl<>(carWashResources, pageable, carWashResources.size());
    }
    private User convertToUserEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToUserResource(User user){
        return mapper.map(user, UserResource.class);
    }

    private CarWash convertToCarWashEntity(SaveCarWashResource resource){
        return mapper.map(resource, CarWash.class);
    }

    private CarWashResource convertToCarWashResource(CarWash carWash){
        return mapper.map(carWash, CarWashResource.class);
    }
}
