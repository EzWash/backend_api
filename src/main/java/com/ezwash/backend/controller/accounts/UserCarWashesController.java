package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserCarWashesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @PostMapping("/users/{userId}/carwashes/{carwashId}")
    public UserResource addUserCarWash(
            @PathVariable Long userId,
            @PathVariable Long carwashId){
        return convertToResource(userService.addUserCarwash(userId, carwashId));
    }
    @DeleteMapping("/users/{userId}/carwashes/{carwashId}")
    public UserResource deleteUserCarWash(
            @PathVariable Long userId,
            @PathVariable Long carwashId){
        return convertToResource(userService.deleteUserCarWash(userId,carwashId));
    }
    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User user){
        return mapper.map(user, UserResource.class);
    }
}
