package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService  userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping ("/user")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        Location location= locationService.getLocationById(resource.getLocation());
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user, location));
    }

    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User user){
        return mapper.map(user, UserResource.class);
    }
}
