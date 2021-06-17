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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    private Contract convertToEntity(SaveContractResource resource){
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract contract){
        return mapper.map(contract, ContractResource.class);
    }

}
