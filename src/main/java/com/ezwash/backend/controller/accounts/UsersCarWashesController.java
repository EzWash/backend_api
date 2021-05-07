package com.ezwash.backend.controller.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.resource.accounts.CarWashResource;
import com.ezwash.backend.resource.accounts.SaveCarWashResource;
import com.ezwash.backend.resource.accounts.SaveUserResource;
import com.ezwash.backend.resource.accounts.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsersCarWashesController {
    @Autowired
    private CarWashService carWashService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users/{userId}/carwashes/{distance}")
    public Page<CarWashResource> getNearCarWashes(@PathVariable Long userId, @PathVariable double distance, Pageable pageable){
        User user = userService.findUserById(userId);
        List<CarWashResource> resources = carWashService.getCarWashesLessThanDistance(user, distance, pageable)
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    private CarWash convertToEntity(SaveCarWashResource resource){
        return mapper.map(resource, CarWash.class);
    }

    private CarWashResource convertToResource(CarWash carWash){
        return mapper.map(carWash, CarWashResource.class);
    }
}
