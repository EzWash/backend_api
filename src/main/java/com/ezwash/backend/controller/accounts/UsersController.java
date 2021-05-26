package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private UserService  userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create Users", description = "Create users", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping ("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        Location location= locationService.getLocationById(resource.getLocation());
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user, location));
    }

    @Operation(summary = "Update User", description = "Update User's information", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's info updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId,@Valid @RequestBody SaveUserResource resource){
        User user=convertToEntity(resource);
        return convertToResource(userService.updateUser(userId,user));

    }
    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User user){
        return mapper.map(user, UserResource.class);
    }
}