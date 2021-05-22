package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Profile;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest  {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CarWashRepository carWashRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }
    @Test
    @DisplayName("when createUser With Valid Attributes Then Returns User")
    public void whenCreateUserWithValidAttributesThenReturnsUser(){
        //Arrange
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        //Location attributes
        Long location_id =1L;
        String address= "Av. El Ejército 1062";
        double lattitude = -11.145312;
        double longitude = -72.123613;

        Location location = new Location()
                .setId(location_id)
                .setAddress(address)
                .setLattitude(lattitude)
                .setLongitude(longitude);

        User user = (User) new User()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        when(userRepository.save(user)).thenReturn((User) new User()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender)
                .setId(1L));
        //Act
        User createdUser = userService.createUser(user,location);

        //Assert
        assertThat(createdUser.getFirst_name()).isEqualTo(first_name);
        assertThat(createdUser.getLast_name()).isEqualTo(last_name);
    }

    @Test
    @DisplayName("when addUserCarWash with Valid UserId and CarWashId Then Returns User")
    public void whenAddUserCarWashWithValidUserIdAndCarWashIdThenReturnsUser(){
        // Arrange
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        // Location attributes User
        Long location_id =1L;
        String address= "Av. El Ejército 1062";
        double lattitude = -11.145312;
        double longitude = -72.123613;

        //Location attributes Car Wash
        Long location_id2 = 2L;
        String address2 = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude2 = -12.104061;
        double longitude2 = -76.962902;

        //CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        Location location = new Location()
                .setId(location_id)
                .setAddress(address)
                .setLattitude(lattitude)
                .setLongitude(longitude);

        Location location1 = new Location()
                .setId(location_id2)
                .setAddress(address2)
                .setLattitude(lattitude2)
                .setLongitude(longitude2);

        List<CarWash> likedCarWashes = new ArrayList<>();

        User user = (User) new User()
                .setId(1L)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        user.setPassword(password);
        user.setLikedCarwashes(likedCarWashes);

        CarWash carWash = new CarWash()
                .setId(1L)
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        when(carWashRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(carWash));

        when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));

        when(userRepository.save(user))
                .thenReturn(user);

        // Act
        User foundUser = userService.addUserCarwash(1L, 1L);

        // Assert
        assertThat(foundUser.getLikedCarwashes().contains(carWash))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("when addUserCarWash with Invalid CarWashId Then Returns Resource Not Found Exepcion")
    public void whenAddUserCarWashWithInvalidCarWashIdThenReturnsResourceNotFoundException(){
        //Location attributes Car Wash
        Long location_id2 = 2L;
        String address2 = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude2 = -12.104061;
        double longitude2 = -76.962902;

        //CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        String template = "Resource %s not found for %s with value %s";

        CarWash carWash = new CarWash()
                .setId(1L)
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        String expectedMessage = String.format(template, "Car Wash", "Id", 2L);

        when(carWashRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.addUserCarwash(1L, 2L);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when addUserCarWash with Invalid UserId Then Returns Resource Not Found Exception")
    public void whenAddUserCarWashWithInvalidUserIdThenReturnsResourceNotFoundException(){
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        // Location attributes User
        Long location_id =1L;
        String address= "Av. El Ejército 1062";
        double lattitude = -11.145312;
        double longitude = -72.123613;

        List<CarWash> likedCarWashes = new ArrayList<>();

        User user = (User) new User()
                .setId(1L)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        user.setPassword(password);
        user.setLikedCarwashes(likedCarWashes);

        //Location attributes Car Wash
        Long location_id2 = 2L;
        String address2 = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude2 = -12.104061;
        double longitude2 = -76.962902;

        //CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        String template = "Resource %s not found for %s with value %s";

        CarWash carWash = new CarWash()
                .setId(1L)
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        String expectedMessage = String.format(template, "User", "Id", 2L);

        when(carWashRepository.findById(1L))
                .thenReturn(Optional.ofNullable(carWash));

        when(userRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.addUserCarwash(2L, 1L);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}