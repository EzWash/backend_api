package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.Profile;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.UserRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest  {
    @MockBean
    private UserRepository userRepository;

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
        String username= "mauCastle";
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
                .setUsername(username)
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        when(userRepository.save(user)).thenReturn((User) new User()
                .setUsername(username)
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
        assertThat(createdUser.getUsername()).isEqualTo(username);
    }

}