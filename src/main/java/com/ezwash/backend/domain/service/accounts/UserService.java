package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;

public interface UserService {
    User createUser(User user);
    Location getLocationById(Long id);
}
