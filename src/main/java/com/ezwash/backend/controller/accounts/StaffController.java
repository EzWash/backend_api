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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Update a Car Wash's staff", description = "Update a staff person given the Car Wash ID and the Staff ID", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff updated successfully", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/carwashes/{carwashId}/staff/{staffId}")
    public StaffResource updateStaff(
            @PathVariable Long carwashId,
            @PathVariable Long staffId,
            @Valid @RequestBody SaveStaffResource resource) {
        return convertToResource(staffService.updateStaff(carwashId, staffId, convertToEntity(resource)));
    }

    @Operation(summary = "Get a Car Wash's staff", description = "Get a staff person given the Car Wash D", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff obtained successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/{carWashId}/staff")
    public List<Staff> getStaffByCarWashId(@PathVariable(value = "carWashId") Long Id){

        return staffService.getStaffByCarWashId(Id);
    }

    @Operation(summary = "Delete Staff", description = "Delete Staff's CarWash", tags = {"Staff"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff's carwash deleted successfully", content = @Content(mediaType = "application/json"))
    })

    @DeleteMapping("/carwashes/{idStaff}/staff")
    public ResponseEntity<?> deleteStaffId (@PathVariable Long idStaff){
        return staffService.deleteStaffById(idStaff);
    }

    private Staff convertToEntity(SaveStaffResource resource){return mapper.map(resource,Staff.class);}
    private StaffResource convertToResource(Staff staff){return mapper.map(staff,StaffResource.class);}
}
