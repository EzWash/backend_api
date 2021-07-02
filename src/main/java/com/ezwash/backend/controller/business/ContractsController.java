package com.ezwash.backend.controller.business;


import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.service.business.ContractService;
import com.ezwash.backend.resource.business.ContractResource;
import com.ezwash.backend.resource.business.SaveContractResource;
import com.ezwash.backend.resource.business.SaveServiceResource;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ContractsController {
    @Autowired
    private ContractService contractService;

    @Autowired
    private ModelMapper mapper;


    @Operation(summary = "Update a Contract Staff", description = "Update Contract Staff given Staff Id and Contract Id", tags = {"Contracts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/contracts/{contractId}/staff/{staffId}")
    public ContractResource designateStaffToContract(@PathVariable Long contractId, @PathVariable Long staffId){
       return convertToResource(contractService.designateStaffToContract(contractId, staffId));
    }

    @Operation(summary = "Get Contracts by State and Customer Id", description = "Get all Contracts that their States are equals to given State and Customer Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contracts by State got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/contracts/states/{state}")
    public List<ContractResource> getContractsByState(@PathVariable String state, @PathVariable Long customerId){
        List<Contract> contractList = contractService.getContractsByState(state, customerId);
        List<ContractResource> contractResourceList = contractList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return contractResourceList;
    }


    @Operation(summary = "Get Contracts by State Not and Customer Id", description = "Get all Contracts that their States are not to given State and Customer Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contracts by State got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/contracts/states/not/{state}")
    public List<ContractResource> getContractsByStateNot(@PathVariable String state, @PathVariable Long customerId){
        List<Contract> contractList = contractService.getContractsByStateNot(state, customerId);
        List<ContractResource> contractResourceList = contractList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return contractResourceList;
    }

    @Operation(summary = "Update a Contract State", description = "Update Contract State given Contract Id and State", tags = {"Contracts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/contracts/{contractId}/states/{state}")
    public ContractResource updateContractState(@PathVariable Long contractId, @PathVariable String state){
       return convertToResource(contractService.updateContractState(contractId, state));
    }

    @Operation(summary = "Create Contract", description = "Create Contract through Customer Id, CarWash Id and Staff Id", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/customers/{customerId}/carwashes/{carWashId}/staff/{staffId}/contracts")
    public ContractResource createContract(@Valid @RequestBody SaveContractResource resource, @PathVariable Long customerId, @PathVariable Long carWashId, @PathVariable Long staffId){
        Contract contract = convertToEntity(resource);
        return convertToResource(contractService.createContract(contract,carWashId,customerId,staffId));
    }


    private Contract convertToEntity(SaveContractResource resource){
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract contract){
        ContractResource resource = mapper.map(contract, ContractResource.class);
        resource.setCarWash_id(contract.getStaff().getCarWash().getId());
        resource.setCarWash_name(contract.getStaff().getCarWash().getName());
        resource.setStaff_id(contract.getStaff().getId());
        resource.setCustomer_id(contract.getCustomer().getId());
        return resource;
    }


}
