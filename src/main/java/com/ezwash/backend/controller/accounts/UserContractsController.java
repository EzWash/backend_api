package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import com.ezwash.backend.resource.business.ContractResource;
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
public class UserContractsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all Contracts from User Contract List",description = "Get all Services from User's Contract List through the User Id",tags = {"User Services"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Services added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/contracts")
    public Page<ContractResource>getUserContract(@PathVariable Long userId, Pageable pageable){
        Page<Contract>contractPage=userService.getContractList(userId,pageable);
        List<ContractResource>contractResources=contractPage.getContent()
                .stream()
                .map(this::convertToContractResource)
                .collect(Collectors.toList());

        return new PageImpl<>(contractResources,pageable,contractResources.size());
    }

    private ContractResource convertToContractResource(Contract contract){
        return mapper.map(contract,ContractResource.class);
    }
}
