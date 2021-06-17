package com.ezwash.backend.controller.business;


import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.service.business.ContractService;
import com.ezwash.backend.resource.business.ContractResource;
import com.ezwash.backend.resource.business.SaveContractResource;
import com.ezwash.backend.resource.business.SaveServiceResource;
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



    private Contract convertToEntity(SaveContractResource resource){
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract contract){
        return mapper.map(contract, ContractResource.class);
    }
}
