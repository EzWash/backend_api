package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.domain.service.geographic.LocationService;
import com.ezwash.backend.resource.accounts.SaveCustomerResource;
import com.ezwash.backend.resource.accounts.CustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomersController {
    @Qualifier("customerServiceImpl")
    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create Customers", description = "Create users", tags = {"Security"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping ("/auth/customers")
    public CustomerResource createUser(@Valid @RequestBody SaveCustomerResource resource) throws ParseException {
        Location location= locationService.getLocationById(resource.getLocation());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Date birth_date = simpleDateFormat.parse(resource.getBirth_date());
        Customer customer = convertToEntity(resource);
        customer.setBirth_date(birth_date);
        return convertToResource(customerService.createCustomer(customer, location));
    }

    @Operation(summary = "Update User", description = "Update User's information", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's info updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/customers/{customerId}")
    public CustomerResource updateUser(@PathVariable Long customerId, @Valid @RequestBody SaveCustomerResource resource){
        Customer customer =convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(customerId, customer));

    }
    private Customer convertToEntity(SaveCustomerResource resource){
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer customer){
        return mapper.map(customer, CustomerResource.class);
    }
}