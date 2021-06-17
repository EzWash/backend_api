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


    @Operation(summary = "Update a Contract's Staff", description = "Update Contract's Staff given Staff Id and Contract Id", tags = {"Contracts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/contracts/{contractId}/staff/{staffId}")
    public ContractResource designateStaffToContract(@PathVariable Long contractId, @PathVariable Long staffId){
       return convertToResource(contractService.designateStaffToContract(contractId, staffId));
    }

    @Operation(summary = "Get Contracts by State", description = "Get all Contracts that their States are equals to given State", tags = {"Contracts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contracts by State got successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/contracts/states/{state}")
    public Page<ContractResource> getContractsByState(@PathVariable String state, Pageable pageable){
        Page<Contract> contractPage = contractService.getContractsByState(state, pageable);
        List<ContractResource> contractResourceList = contractPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(contractResourceList,pageable,contractResourceList.size());
    }


    private Contract convertToEntity(SaveContractResource resource){
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract contract){
        return mapper.map(contract, ContractResource.class);
    }
}
