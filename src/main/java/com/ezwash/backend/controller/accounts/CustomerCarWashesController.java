package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import com.ezwash.backend.resource.accounts.SaveCustomerResource;
import com.ezwash.backend.resource.accounts.CustomerResource;
import com.ezwash.backend.resource.business.CommentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CustomerCarWashesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CarWashService carWashService;

    @Operation(summary = "Add Car Wash to User's Liked List", description = "Add Car Wash to User's Liked list through the User Id and Car Wash Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash added successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/customers/{customerId}/carwashes/{carwashId}")
    public CustomerResource addUserCarWash(
            @PathVariable Long customerId,
            @PathVariable Long carwashId){
        return convertToUserResource(customerService.addCustomerCarwash(customerId, carwashId));
    }

    @Operation(summary = "Get all Car Washes from User's liked list", description = "Get all Car Washes from the User's liked list through the User Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash added successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/carwashes")
    public Page<CarWashResource> getLikedList(@PathVariable Long customerId, Pageable pageable){
        Page<CarWash> carWashPage = customerService.getLikedList(customerId, pageable);
        List<CarWashResource> carWashResources = carWashPage.getContent()
                .stream()
                .map(this::convertToCarWashResource)
                .collect(Collectors.toList());
        return new PageImpl<>(carWashResources, pageable, carWashResources.size());
    }
    @Operation(summary = "Get CarWash Qualification",description = "Get CarWash Qualification through CarWash Id",tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Car Wash Qualification added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/carwashes/{carwashId}/qualification")
    public int getCarWashQualification(@PathVariable Long carwashId){
        return carWashService.getCarWashQualification(carwashId);
    }
    @Operation(summary = "Get CarWash Comment List",description = "Get CarWash Comment List through CarWashId",tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Car Wash CommentList added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/carwashes/{carwashId}")
    public Page<CommentResource>getCarWashCommentList(@PathVariable Long carwashId,Pageable pageable){
        Page<Comment>commentPage=carWashService.getCarWashComments(carwashId,pageable);
        List<CommentResource>commentResources=commentPage.getContent()
                .stream()
                .map(this::convertToCommentResource)
                .collect(Collectors.toList());
        return new PageImpl<>(commentResources,pageable,commentResources.size());

    }
    @Operation(summary = "Delete a Car Wash from User's liked list", description = "Delete a Car Wash from User's liked list through the User Id and Car Wash Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Wash deleted successfully", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/customers/{customerId}/carwashes/{carwashId}")
    public CustomerResource deleteUserCarWash(
            @PathVariable Long customerId,
            @PathVariable Long carwashId){
        return convertToUserResource(customerService.deleteCustomerCarWash(customerId,carwashId));
    }

    private Customer convertToUserEntity(SaveCustomerResource resource){

        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToUserResource(Customer customer){
        return mapper.map(customer, CustomerResource.class);
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
