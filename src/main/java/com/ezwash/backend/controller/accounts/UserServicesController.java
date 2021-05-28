package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import com.ezwash.backend.resource.business.SaveServiceResource;
import com.ezwash.backend.resource.business.ServiceResource;
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
public class UserServicesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all Services from User Service List",description = "Get all Services from User's Service List through the User Id",tags = {"User Services"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Services added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/services")
    public Page<ServiceResource>getUserServices(@PathVariable Long userId, Pageable pageable){
        Page<Service>servicePage=userService.getServiceList(userId,pageable);
        List<ServiceResource>serviceResources=servicePage.getContent()
                .stream()
                .map(this::convertToServiceResource)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceResources,pageable,serviceResources.size());
    }

    private User convertToUserEntity(SaveUserResource resource){
        return mapper.map(resource,User.class);
    }
    private UserResource convertToUserResource(User user){
        return mapper.map(user,UserResource.class);
    }
    private Service convertToServiceEntity(SaveServiceResource resource){
        return mapper.map(resource,Service.class);
    }
    private ServiceResource convertToServiceResource(Service service){
        return mapper.map(service,ServiceResource.class);
    }
}
