package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarWashRepository carWashRepository;

    @Override
    public User createUser(User user, Location location) {
        user.setLocation(location);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public User addUserCarwash(Long userId, Long carWashId) {
        CarWash carWash = carWashRepository.findById(carWashId)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", carWashId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addCarWashToLikedList(carWash)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<CarWash> getLikedList(Long userId, Pageable pageable) {
        List<CarWash> carWashes = findUserById(userId).getLikedCarwashes();
        return new PageImpl<>(carWashes, pageable, carWashes.size());
    }
}