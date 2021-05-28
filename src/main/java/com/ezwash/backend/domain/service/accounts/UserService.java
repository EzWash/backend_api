package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user, Location location);
    User findUserById(Long id);
    User addUserCarwash(Long userId, Long carWashId);
    User deleteUserCarWash(Long userId,Long carWashId);
    User updateUser(Long userId,User userRequest);
    Page<CarWash> getLikedList(Long userId, Pageable pageable);
    Page<Service> getServiceList(Long userId,Pageable pageable);
}
