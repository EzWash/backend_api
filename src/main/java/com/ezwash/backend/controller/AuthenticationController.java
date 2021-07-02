package com.ezwash.backend.controller;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.service.DefaultUserDetailsService;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.resource.accounts.UserDTO;
import com.ezwash.backend.service.communication.AuthenticationRequest;
import com.ezwash.backend.service.communication.AuthenticationResponse;
import com.ezwash.backend.util.JwtCenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCenter tokenCenter;
    @Autowired
    private DefaultUserDetailsService userDetailsService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CarWashService carWashService;

    @Operation(summary = "Login User", description = "Authenticates an User", tags = {"Security"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated success", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<?> generateAuthenticationToken(
            @RequestBody AuthenticationRequest request)
            throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("Password: " + request.getPassword());
        String token = tokenCenter.generateToken(userDetails);
        Customer customer = customerService.findCustomerByEmail(userDetails.getUsername());
        if(customer != null){
            return ResponseEntity.ok(new
                    AuthenticationResponse(convertCustomerToUserDTO(customer), token));
        }else{
            CarWash carWash = carWashService.findCarWashByEmail(userDetails.getUsername());
            if(carWash != null)
                return ResponseEntity.ok(new
                    AuthenticationResponse(convertCarwashToUserDTO(carWash), token));
        }
        return ResponseEntity.badRequest().build();
    }

    private void authenticate(String username, String password)
            throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

   private UserDTO convertCustomerToUserDTO(Customer customer){
        return new UserDTO()
                .setId(customer.getId())
                .setEmail(customer.getEmail())
                .setName(customer.getFirst_name())
                .setRole("CUSTOMER");
    }
   private UserDTO convertCarwashToUserDTO(CarWash carWash) {
        return new UserDTO()
                .setId(carWash.getId())
                .setEmail(carWash.getEmail())
                .setName(carWash.getName_owner())
                .setRole("CARWASH");
   }
}
