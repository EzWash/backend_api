package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user, Location location) {
        user.setLocation(location);
        return userRepository.save(user);
    }

    @Override
    public User getUserByFirst_name(String first_name) {
        return userRepository.findByFirst_name(first_name)
                .orElseThrow(() -> new ResourceNotFoundException("User", "first_name", first_name));
    }
}
