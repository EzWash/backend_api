package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;

import java.util.List;

public interface UserService {
    User createUser(User user, Location location);
    User findUserById(Long id);
    User addUserCarwash(Long userId, Long carWashId);
    User deleteUserCarWash(Long userId,Long carWashId);
    User updateUser(Long userId,User userRequest);

}
