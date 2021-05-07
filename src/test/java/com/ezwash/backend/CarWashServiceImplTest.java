package com.ezwash.backend;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.service.accounts.CarWashServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CarWashServiceImplTest {
    @MockBean
    private CarWashRepository carWashRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private CarWashService carWashService;

    @TestConfiguration
    static class CarWashServiceImplTestConfiguration{
        @Bean
        public CarWashService carWashService() {return new CarWashServiceImpl();}
    }

    @Test
    @DisplayName("when createCarWash With Valid Attributes Then Returns CarWash")
    public void whenCreateCarWashWithValidAttributesThenReturnsCarWash(){
        // Arrange
        //Request Body CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        //Saved Location attributes
        Long location_id = 1L;
        String address = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude = -12.104061;
        double longitude = -76.962902;

        Location location_test = new Location()
                .setId(location_id)
                .setAddress(address)
                .setLattitude(lattitude)
                .setLongitude(longitude);

        CarWash carWash = new CarWash()
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        when(carWashRepository.save(carWash))
                .thenReturn(
                        new CarWash()
                        .setDescription(description)
                        .setName(name)
                        .setName_owner(name_owner)
                        .setAvailable(1)
                        .setQualification(0)
                        .setId(1L)
                        .setLocation(location_test)
                );
        // Act
        CarWash createdCarWash = carWashService.createCarWash(carWash, location_test);

        // Assert
        assertThat(createdCarWash.getName()).isEqualTo(name);
        assertThat(createdCarWash.getLocation().getId()).isEqualTo(location_test.getId());
    }

    @Test
    @DisplayName("when editCarWash With Valid Attributes Then Returns CarWash")
    public void whenEditCarWashWithValidAttributesThenReturnsCarWash(){
        // Arrange
        //Request Body CarWash attributes

        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        //Saved Location attributes
        Long location_id = 1L;
        String address = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude = -12.104061;
        double longitude = -76.962902;

        Location location_test = new Location()
                .setId(location_id)
                .setAddress(address)
                .setLattitude(lattitude)
                .setLongitude(longitude);

        CarWash carWash = new CarWash()
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner)
                .setId(1L)
                .setLocation(location_test);

        when(carWashRepository.findById(1L)).thenReturn(Optional.ofNullable(carWash));
        when(carWashRepository.save(carWash))
                .thenReturn(
                        new CarWash()
                                .setDescription("New description")
                                .setName(name)
                                .setName_owner(name_owner)
                                .setAvailable(1)
                                .setQualification(0)
                                .setId(1L)
                                .setLocation(location_test)
                );
        // Act

        CarWash updatedCarWash = carWashService.editCarWash(1L,carWash);



        // Assert
        assertThat(updatedCarWash.getDescription()).isEqualTo("New description");

    }
    //No sure about unhappy path's existence
    /*
    @Test
    @DisplayName("when createCarWash With Invalid Attributes Then Returns Resource Bad Request Exception")
    public void whenCreateCarWashWithInvalidAttributesThenReturnsResourceBadRequestException(){
        // Arrange
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Long location = 1L;
        Integer available = 1;
        Integer qualification = 0;

        Long location_id = 1L;
        String address = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude = -12.104061;
        double longitude = -76.962902;

        Location location_test = new Location()
                .setId(1L)
                .setAddress(address)
                .setLattitude(lattitude)
                .setLongitude(longitude);

        CarWash carWash = new CarWash()
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        when(carWashRepository.save(carWash))
                .thenReturn(
                        new CarWash()
                                .setDescription(description)
                                .setName(name)
                                .setName_owner(name_owner)
                                .setAvailable(available)
                                .setQualification(qualification)
                                .setId(1L)
                                .setLocation(location_test)
                );
        // Act
        //Assert
    }
    */
}
