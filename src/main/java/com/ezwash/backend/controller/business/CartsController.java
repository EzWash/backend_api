package com.ezwash.backend.controller.business;

import com.ezwash.backend.domain.model.business.Cart;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.service.business.CartService;
import com.ezwash.backend.resource.business.CartResource;
import com.ezwash.backend.resource.business.SaveCartResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ezwash.backend.resource.business.ServiceResource;
import com.ezwash.backend.resource.business.SaveServiceResource;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CartsController {
    @Autowired
    private ModelMapper mapper;

    @Qualifier("cartServiceImpl")
    @Autowired
    private CartService cartService;

    @Operation(summary = "Add Service to Cart", description = "Add a service to a cart through Cart Id", tags = {"Carts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service added successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/carts/{cartId}/services/{serviceId}")
    public CartResource addServiceToCart(
            @PathVariable Long cartId,
            @PathVariable Long serviceId){
        return convertToUserResource(cartService.addServiceToCart(cartId, serviceId));
    }

    @Operation(summary = "Get all Services from User's Cart", description = "Get all services from the User's Cart through the User Id", tags = {"Carts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{cartId}/carts")
    public List<ServiceResource> getLikedList(@PathVariable Long cartId){
        List<ServiceResource> serviceResources = cartService.getServiceByCartId(cartId)
                .stream()
                .map(this::convertToServiceResource)
                .collect(Collectors.toList());
        return serviceResources;
    }

    @Operation(summary = "Delete a Service from User's Cart", description = "Delete a Service from User's Cart through the Cart Id", tags = {"Carts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service deleted successfully", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/cart/{cartId}/services/{serviceId}")
    public CartResource deleteUserCarWash(
            @PathVariable Long cartId,
            @PathVariable Long serviceId){
        return convertToUserResource(cartService.deleteServiceFromCart(cartId,serviceId));
    }

    private Cart convertToCartEntity(SaveCartResource resource){

        return mapper.map(resource, Cart.class);
    }

    private CartResource convertToUserResource(Cart cart){
        CartResource resource =  mapper.map(cart, CartResource.class);
        resource.setCartId(cart.getCustomer().getId());
        return resource;
    }

    private Service convertToServiceEntity(SaveServiceResource resource){

        return mapper.map(resource, Service.class);
    }

    private ServiceResource convertToServiceResource(Service service){
        ServiceResource resource =  mapper.map(service, ServiceResource.class);
        return resource;
    }
}
