package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.service.accounts.CustomerService;
import com.ezwash.backend.resource.business.ContractResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerContractsController {
    @Autowired
    private ModelMapper mapper;


    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get all Contracts from User Contract List",description = "Get all Services from User's Contract List through the User Id",tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Services added successfully",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/contracts")
    public List<ContractResource>getUserContract(@PathVariable Long customerId){
        List<Contract>contractList= customerService.getContractList(customerId);
        List<ContractResource> contractResourceList = contractList.stream().map(this::convertToContractResource)
                .collect(Collectors.toList());
        return contractResourceList;
    }

    private ContractResource convertToContractResource(Contract contract){
        return mapper.map(contract,ContractResource.class);
    }
}
