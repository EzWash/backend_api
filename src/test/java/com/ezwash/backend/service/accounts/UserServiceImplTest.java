package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Profile;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.accounts.User;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.model.business.Service;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CarWashRepository carWashRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("When createUser With Valid Attributes Then Returns User")
    public void whenCreateUserWithValidAttributesThenReturnsUser() {
        //Arrange
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number = "987655325";
        String gender = "M";
        String password = "$3fsdg";

        //Location attributes
        Long location_id = 1L;
        String address = "Av. El Ejército 1062";
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
        User createdUser = userService.createUser(user, location);

        //Assert
        assertThat(createdUser.getFirst_name()).isEqualTo(first_name);
        assertThat(createdUser.getLast_name()).isEqualTo(last_name);
    }
    @Test
    @DisplayName("When updateUser With Valid UserId and Valid Attributes Then Returns User")
    public void whenUpdateUserWithValidUserIdAndValidAttributesThenReturnsUser(){
        // Arrange
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        //Update Data

        String first_name2="Lucho Luis";
        String last_name2="Quispe Quispe";
        String email2="quispecito2@gmail.com";
        String phone_number2="969420777";
        String gender2="M";



        // Location attributes User
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
        User userUpdate=(User) new User()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name2)
                .setLast_name(last_name2)
                .setEmail(email2)
                .setPhone_number(phone_number2)
                .setGender(gender2);

        when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));

        when(userRepository.save(user))
                .thenReturn(user);
        //Act
        User foundUser=userService.updateUser(1L,userUpdate);
        //Assert
        assertThat(foundUser.getFirst_name()).isEqualTo(first_name2);
        assertThat(foundUser.getLast_name()).isEqualTo(last_name2);

    }
    @Test
    @DisplayName("When updateUser With Invalid UserId Then Returns ResourceNotFoundException")
    public void whenUpdateUserWithInvalidUserIdThenReturnsResourceNotFoundException(){
        // Arrange
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        //Update Data

        String first_name2="Lucho Luis";
        String last_name2="Quispe Quispe";
        String email2="quispecito2@gmail.com";
        String phone_number2="969420777";
        String gender2="M";



        // Location attributes User
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
        User userUpdate=(User) new User()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name2)
                .setLast_name(last_name2)
                .setEmail(email2)
                .setPhone_number(phone_number2)
                .setGender(gender2);
        String template = "Resource %s not found for %s with value %s";

        String expectedMessage=String.format(template,"User","Id",2L);
        when(userRepository.findById(2L))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.updateUser(2L,userUpdate);
        });
        //Assert
            assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When addUserCarWash With Valid UserId And CarWashId Then Returns User")
    public void whenAddUserCarWashWithValidUserIdAndCarWashIdThenReturnsUser() {
        // Arrange
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number = "987655325";
        String gender = "M";
        String password = "$3fsdg";

        // Location attributes User
        Long location_id = 1L;
        String address = "Av. El Ejército 1062";
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
        String name_owner = "Carlos";

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

        String template = "Resource %s not found for %s with value %s";
        // Act
        User foundUser = userService.addUserCarwash(1L, 1L);

        // Assert
        assertThat(foundUser.getLikedCarwashes().contains(carWash))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("When addUserCarWash With Invalid CarWashId Then Returns ResourceNotFoundException")
    public void whenAddUserCarWashWithInvalidCarWashIdThenReturnsResourceNotFoundException() {
        //Location attributes Car Wash
        Long location_id2 = 2L;
        String address2 = "Prolongación Primavera 2390, Lima 15023, Peru";
        double lattitude2 = -12.104061;
        double longitude2 = -76.962902;

        //CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos";

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
    @DisplayName("When deleteUserCarWash With Invalid User Id Then Returns ResourceNotFoundException")
    public void whenDeleteUserCarWashWithInvalidUserIdThenReturnsResourceNotFoundException(){
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
            User foundUser = userService.deleteUserCarWash(2L, 1L);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When deleteUserCarWash With Invalid CarWashId Then ReturnsResourceNotFoundException")
    public void whenDeleteUserCarWashWithInvalidCarWashIdThenReturnsResourceNotFoundException(){
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
            User foundUser = userService.deleteUserCarWash(1L, 2L);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When addUserCarWash With Invalid User Id Then Returns ResourceNotFoundException")
    public void whenAddUserCarWashWithInvalidUserIdThenReturnsResourceNotFoundException() {
        // User Data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number = "987655325";
        String gender = "M";
        String password = "$3fsdg";

        // Location attributes User
        Long location_id = 1L;
        String address = "Av. El Ejército 1062";
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
        String name_owner = "Carlos";

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
            User foundedUser = userService.addUserCarwash(2L, 1L);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getLikedList With Valid UserId And Valid CarWashId Then Returns Liked List of Car Washes")
    public void whenGetLikedListWithValidUserIdAndValidCarWashIdThenReturnsLikedListoFCarWashes(){
        //Arrange
        // User data
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        // CarWash data
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos" ;

        CarWash carWash = new CarWash()
                .setId(1L)
                .setDescription(description)
                .setName(name)
                .setName_owner(name_owner);

        List<CarWash> likedCarWashes = new ArrayList<>();
        likedCarWashes.add(carWash);

        User user = (User) new User()
                .setId(1L)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        user.setPassword(password);
        user.setLikedCarwashes(likedCarWashes);

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 5;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        // Act
        Page<CarWash> carWashPage = userService.getLikedList(1L, pageable);

        // Assert
        assertThat(carWashPage.getTotalElements())
                .isEqualTo(1L);

    }
  
        

    @Test
    @DisplayName("When getLikedList With Invalid User Id Then Returns ResourceNotFoundException")
    public void whenGetLikedListWithInvalidUserIdThenReturnsResourceNotFoundException(){
        // Arrange
        Long userId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "User", "Id", userId);

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 5;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        // Act
        Throwable exception = catchThrowable(() -> {
            Page<CarWash> carWashPage = userService.getLikedList(userId, pageable);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
  
    @Test 
    @DisplayName("When deleteUserCarWash With Valid User Id And Valid Car Wash Id then Returns User")
    public void whenDeleteUserCarWashWithValidUserIdAndValidCarWashIdThenReturnsUser(){
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

        String template = "Resource %s not found for %s with value %s";

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

        User foundUser = userService.addUserCarwash(1L, 1L);

        // Act
        userService.deleteUserCarWash(1L,1L);

        // Assert
        assertThat(foundUser.getLikedCarwashes().contains(carWash))
                .isEqualTo(false);

    }
    @Test
    @DisplayName("When Get Service List with inValid UserId then returns Resource Not Found Exception")
    public void whenGetServiceListWithInvalidUserIdThenReturnsResourceNotFoundException(){
        // Arrange
        //ServiceData
        String name = "Encerado";
        String description = "Ofrecemos el servicio perfecto de encerado para que usted pueda obtener ";
        String details = "Aceite, Pulido, Encerado";
        Integer is_promotion= 0;
        double price = 30.9;
        Service service = new Service()
                .setName(name)
                .setDescription(description)
                .setDetails(details)
                .setIs_promotion(is_promotion)
                .setPrice(price);


        Long userId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "User", "Id", userId);

        List<Service>serviceList=new ArrayList<>();
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 5;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        // Act
        Throwable exception = catchThrowable(() -> {
            Page<Contract> contractPage = userService.getContractList(userId,pageable);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When Get Service List with Valid UserId then returns ServiceList")
    public void whenGetServiceListWithValidUserIdThenReturnsServiceList(){
        // Arrange
        //ServiceData
        String name = "Encerado";
        String description = "Ofrecemos el servicio perfecto de encerado para que usted pueda obtener ";
        String details = "Aceite, Pulido, Encerado";
        Integer is_promotion= 0;
        double price = 30.9;
        Service service = new Service()
                .setId(1L)
                .setName(name)
                .setDescription(description)
                .setDetails(details)
                .setIs_promotion(is_promotion)
                .setPrice(price);

        //UserData
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        String password = "$3fsdg";

        User user = (User) new User()
                .setId(1L)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);
        user.setPassword(password);

        //ContractData
        String state="terminado";
        Date date=new Date();
        Report report=new Report();
        Staff staff=new Staff();
        Contract contract=new Contract()
                .setDate(date)
                .setService(service)
                .setUser(user)
                .setReport(report)
                .setId(1L)
                .setStaff(staff)
                .setState(state);

        List<Contract>contractList=new ArrayList<>();
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 5;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        contractList.add(contract);
        user.setContractList(contractList);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        // Act
        Page<Contract> contractPage = userService.getContractList(1L, pageable);

        // Assert
        assertThat(contractPage.getTotalElements())
                .isEqualTo(1L);
    }

}
