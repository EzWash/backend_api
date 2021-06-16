package com.ezwash.backend.service.interactions;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.interactions.Vehicle;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.repository.interactions.VehicleRepository;
import com.ezwash.backend.domain.service.interactions.VehicleService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VehicleServiceImplTest {
    @MockBean()
    private VehicleRepository vehicleRepository;

    @MockBean
    private CustomerRepository customerRepository;

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
    @DisplayName("When createVehicle With Valid Attributes Then Returns Vehicle")
    public void whenCreateVehicleWithValidAttributesThenReturnsVehicle(){
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

    @Test
    @DisplayName("When deleteCarById With Valid Id Then Delete Vehicle")
    public void whenDeleteCarByIdWithValidIdThenDeleteVehicle(){
        //Arrange
        Vehicle vehicle = new Vehicle().setId(1L);
        when (vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        Vehicle vehicle2= vehicleService.createVehicle(vehicle);

        //Act
        ResponseEntity<?> response= vehicleService.deleteCarById(1L);

        //Assert

        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    @DisplayName("When deleteCarById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenDeleteCarByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        String template = "Resource %s not found for %s with value %s";
        Long vehicleId = 1L;
        String expectedMessage = String.format(template, "Vehicle", "Id", vehicleId);
        when (vehicleRepository.findById(1L)).thenReturn(Optional.empty());
        //Act
        Throwable exception = Assertions.catchThrowable(() -> {
            ResponseEntity<?> response= vehicleService.deleteCarById(1L);
        });
        //Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}