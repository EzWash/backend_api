package com.ezwash.backend.service.interactions;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.repository.interactions.VehicleRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import com.ezwash.backend.service.accounts.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VehicleServiceImplTest {
    @MockBean()
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private VehicleService vehicleService;

    @TestConfiguration
    static class VehicleServiceImplTestConfiguration{
        @Bean
        public VehicleService vehicleService(){
            return new VehicleServiceImpl();
        }
    }

    @Test
    @DisplayName("when createVehicle With Valid Attributes Then Returns Vehicle")
    public void whencreateVehicleWithValidAttributesThenReturnsVehicle(){
        //Arrange
        String model = "A2";
        String brand = "Audi";
        String registration_plate= "ABC-234";

        Vehicle vehicle = new Vehicle().setModel(model).setBrand(brand).setRegistration_plate(registration_plate);

        when(vehicleRepository.save(vehicle)).thenReturn(new Vehicle()
                .setModel(model)
                .setBrand(brand)
                .setRegistration_plate(registration_plate)
                .setId(1L));
        // Act
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        // Assert
        assertThat(createdVehicle.getModel()).isEqualTo(model);
        assertThat(createdVehicle.getBrand()).isEqualTo(brand);
    }
}