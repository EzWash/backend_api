package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.SaveUserResource;
import com.ezwash.backend.resource.UserResource;
import com.ezwash.backend.service.accounts.UserServiceImpl;
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
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @PostMapping ("/user")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        Location location= userService.getLocationById(resource.getLocation());
        User user = convertToEntity(resource);
        user.setLocation(location);
        return convertToResource(userService.createUser(user));
    }
    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }
}
