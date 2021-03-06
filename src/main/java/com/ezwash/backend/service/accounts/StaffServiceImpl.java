package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CarWashRepository carWashRepository;

    @Override
    public Staff createStaff(CarWash carWash, Staff staff) {
        staff.setCarWash(carWash);
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long carWashId, Long staffId, Staff newStaff){
        if(!carWashRepository.existsById(carWashId))
            throw new ResourceNotFoundException("CarWash", "Id", carWashId);
        return staffRepository.findById(staffId).map(
                staff -> {
                    staff.setFirst_name(newStaff.getFirst_name())
                            .setLast_name(newStaff.getLast_name())
                            .setEmail(newStaff.getEmail())
                            .setGender(newStaff.getGender())
                            .setPhone_number(newStaff.getPhone_number());
                    return staffRepository.save(staff);
                }).orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
    }
}
