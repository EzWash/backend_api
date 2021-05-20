package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.resource.accounts.SaveStaffResource;
import com.ezwash.backend.resource.accounts.StaffResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarWashesStaffController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create CarWashes", description = "Create a Car Wash", tags = {"CarWashes and Staff"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarWash returned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/carwashes/{carwashId}/staff")
    public StaffResource createEmployee(@PathVariable Long carwashId, @RequestBody SaveStaffResource resource){
        CarWash carWash = carWashService.findCarWashById(carwashId);
        Staff staff = convertToEntity(resource);
        StaffResource staffResource = convertToResource(staffService.createStaff(carWash, staff))
                .setCarWashId(carwashId);
        return staffResource;
    }

    private Staff convertToEntity(SaveStaffResource resource){return mapper.map(resource, Staff.class);}
    private StaffResource convertToResource(Staff staff){return mapper.map(staff, StaffResource.class);}
}
