package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import com.ezwash.backend.resource.business.CommentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CarWashService carWashService;

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

    @Operation(summary = "Get all Car Washes from User's liked list", description = "Get all Car Washes from the User's liked list through the User Id", tags = {"User CarWashes"})
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
    @Operation(summary = "Get CarWash Qualification",description = "Get CarWash Qualification through CarWash Id",tags = {"User CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Car Wash Qualification added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/carwashes/{carwashId}/qualification")
    public int getCarWashQualification(@PathVariable Long carwashId){
        return carWashService.findCarWashById(carwashId).getQualification();
    }
    @Operation(summary = "Get CarWash Comment List",description = "Get CarWash Comment List through CarWashId",tags = {"User CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Car Wash CommentList added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/carwashes/{carwashId}")
    public Page<CommentResource>getCarWashCommentList(@PathVariable Long carwashId,Pageable pageable){
        List<Comment>comments=carWashService.findCarWashById(carwashId).getCommentList();
        List<CommentResource>commentResources=comments.stream().map(this::convertToCommentResource).collect(Collectors.toList());

        return new PageImpl<>(commentResources,pageable,commentResources.size());

    }
    @Operation(summary = "Delete a Car Wash from User's liked list", description = "Delete a Car Wash from User's liked list through the User Id and Car Wash Id", tags = {"User CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash deleted successfully", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}/carwashes/{carwashId}")
    public UserResource deleteUserCarWash(
            @PathVariable Long userId,
            @PathVariable Long carwashId){
        return convertToUserResource(userService.deleteUserCarWash(userId,carwashId));
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
    private CommentResource convertToCommentResource(Comment comment){
        return mapper.map(comment,CommentResource.class);
    }
}
