package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.resource.accounts.SaveStaffResource;
import com.ezwash.backend.resource.accounts.StaffResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private CarWashService carWashService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/staff/{carwashId}")
    public StaffResource createStaff(@Valid @RequestBody SaveStaffResource resource, @PathVariable long carwashId){
        CarWash carWash=carWashService.findCarWashById(carwashId);
        Staff staff = convertToEntity(resource);
        staff.setCarWash(carWash);
        return convertToResource(staffService.createStaff(staff,carWash));

    }
    private Staff convertToEntity(SaveStaffResource resource){return mapper.map(resource,Staff.class);}
    private StaffResource convertToResource(Staff staff){return mapper.map(staff,StaffResource.class);}



}
