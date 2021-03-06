package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.geographic.LocationRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class CarWashServiceImplTest {
    @MockBean
    private CarWashRepository carWashRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private CarWashService carWashService;

    @TestConfiguration
    static class CarWashServiceImplTestConfiguration {
        @Bean
        public CarWashService carWashService() {
            return new CarWashServiceImpl();
        }
    }

    @Test
    @DisplayName("When createCarWash With Valid Attributes Then Returns CarWash")
    public void whenCreateCarWashWithValidAttributesThenReturnsCarWash() {
        // Arrange
        //Request Body CarWash attributes
        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos";

        //Saved Location attributes
        Long location_id = 1L;
        String address = "Prolongaci??n Primavera 2390, Lima 15023, Peru";
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
    @DisplayName("When editCarWash With Valid Attributes Then Returns CarWash")
    public void whenEditCarWashWithValidAttributesThenReturnsCarWash() {
        // Arrange
        //Request Body CarWash attributes

        String description = "Somos el mejor CarWash de la historia";
        String name = "Limpieza Total";
        String name_owner = "Carlos";

        //Saved Location attributes
        Long location_id = 1L;
        String address = "Prolongaci??n Primavera 2390, Lima 15023, Peru";
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

        CarWash updatedCarWash = carWashService.editCarWash(1L, carWash, location_test);


        // Assert
        assertThat(updatedCarWash.getDescription()).isEqualTo("New description");

    }

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
        String address = "Prolongaci??n Primavera 2390, Lima 15023, Peru";
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

    @Test
    @DisplayName("When getCarWashesLessThanDistance With Valid Distance Then Returns CarWashes")
    public void whenGetCarWashesLessThanDistanceWithValidDistanceThenReturnsCarWashes() {
        // Arrange
        CarWash carWash1 = new CarWash()
                .setId(1L)
                .setAvailable(1)
                .setDescription("Ey amigo, si quieres el mejor servicio de Car Wash, entonces ven con nosotroso.")
                .setName("Quality Car Wash")
                .setName_owner("Ricardo")
                .setQualification(0)
                .setLocation(new Location()
                        .setId(1L)
                        .setAddress("Jr. Garc??a Vill??n 452, Cercado de Lima 15079, Peru")
                        .setLattitude(-12.0421891)
                        .setLongitude(-77.0498078));

        CarWash carWash2 = new CarWash()
                .setId(2L)
                .setAvailable(1)
                .setDescription("Ey amigo, si quieres el mejor servicio de Car Wash, entonces ven con nosotros")
                .setName("Duro Car Wash")
                .setName_owner("Milagros")
                .setQualification(0)
                .setLocation(new Location()
                        .setId(2L)
                        .setAddress("Prolongaci??n Primavera 2390, Lima 15023, Peru")
                        .setLattitude(-12.104061)
                        .setLongitude(-76.962902));

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

        List<CarWash> carWashList = new ArrayList<>();
        carWashList.add(carWash1);
        carWashList.add(carWash2);
        Page<CarWash> carWashes = new PageImpl(carWashList);

        when(carWashRepository.findAll(pageable))
                .thenReturn(carWashes);
        // Act
        Page<CarWash> foundedCarWashes = carWashService.getCarWashesLessThanDistance(-12.0506967, -77.0647996, 5, pageable);
        Page<CarWash> foundedCarWashes2 = carWashService.getCarWashesLessThanDistance(-12.0506967, -77.0647996, 14, pageable);
        // Assert
        assertThat(foundedCarWashes.getTotalElements()).isEqualTo(1);
        assertThat(foundedCarWashes2.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("When getCarWashesLessThanDistance With Invalid Distance Then Returns ResourceNotFoundException")
    public void whenGetCarWashLessthanDistancewithInvalidDistanceThenReturnsResourceNotFoundException() {
        // Arrange
        double distance = 2;
        String template = "Resource %s not found for %s with value %s";
        CarWash carWash = new CarWash()
                .setId(2L)
                .setAvailable(1)
                .setDescription("Ey amigo, si quieres el mejor servicio de Car Wash, entonces ven con nosotros")
                .setName("Duro Car Wash")
                .setName_owner("Milagros")
                .setQualification(0)
                .setLocation(new Location()
                        .setId(2L)
                        .setAddress("Prolongaci??n Primavera 2390, Lima 15023, Peru")
                        .setLattitude(-12.104061)
                        .setLongitude(-76.962902));

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

        List<CarWash> carWashList = new ArrayList<>();
        carWashList.add(carWash);
        Page<CarWash> carWashes = new PageImpl(carWashList);

        when(carWashRepository.findAll(pageable))
                .thenReturn(carWashes);
        String expectedMessage = String.format(template, "CarWash", "Distance", distance);
        // Act

        Throwable exception = catchThrowable(() -> {
            Page<CarWash> foundedCarWashes = carWashService.getCarWashesLessThanDistance(-12.0506967, -77.0647996, distance, pageable);
        });

        // Assert

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getCarWashesById With Existent Id Then Returns CarWash")
    public void whenGetCarWashByIdwithExistentIdThenReturnsCarWash() {
        //Arrange
        String description = "Carros bien limpios";
        String name = "LimpiaAutos";
        String name_owner = "Marco Salas";
        int qualification = 3;
        int available = 2;

        CarWash carWash = new CarWash().setDescription(description).setName_owner(name_owner)
                .setName(name).setQualification(qualification).setAvailable(available);

        when(carWashRepository.findById(1L)).thenReturn(Optional.ofNullable(carWash));

        //Act

        CarWash carWash2 = carWashService.findCarWashById(1L);

        //Assert

        assertEquals(carWash2.getDescription(), description);

    }

    @Test
    @DisplayName("When getCarWashesById With Inexistent Id Then Returns ResourceNotFoundException")
    public void whenGetCarWashByIdwithInexistentIdThenReturnsResourceNotFoundException() {
        //Arrange

        String template = "Resource %s not found for %s with value %s";
        Long carWashId = 1L;
        String expectedMessage = String.format(template, "Car Wash", "Id", carWashId);

        when(carWashRepository.findById(carWashId)).thenReturn(Optional.empty());

        //Act

        Throwable exception = Assertions.catchThrowable(() -> {
            CarWash carWash = carWashService.findCarWashById(carWashId);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    @DisplayName("When getCarWashByQualification With Valid Qualification Then Returns CarWashes")
    public void whenGetCarWashByQualificationwithValidQualificationThenReturnsCarWashes(){
        //Arrange
        String description = "Carros bien limpios";
        String name = "LimpiaAutos";
        String name_owner = "Marco Salas";
        int qualification = 3;
        int available = 2;

        CarWash carWash = new CarWash().setDescription(description).setName_owner(name_owner)
                .setName(name).setQualification(qualification).setAvailable(available);

        String description2 = "Carros bien limpios2";
        String name2 = "LimpiaAutos2";
        String name_owner2 = "Marco Salas2";
        int qualification2 = 3;
        int available2 = 2;

        CarWash carWash2 = new CarWash().setDescription(description2).setName_owner(name_owner2)
                .setName(name2).setQualification(qualification2).setAvailable(available2);

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
        List<CarWash> carWashList = new ArrayList<>();
        carWashList.add(carWash);
        carWashList.add(carWash2);

        when(carWashRepository.findCarWashByQualification(3))
                .thenReturn(carWashList);
        // Act
        Page<CarWash> foundedCarWashesByQualification = carWashService.findByQualification(3,pageable);

        // Assert
        assertThat(foundedCarWashesByQualification.getTotalElements()).isEqualTo(2);

    }

    @Test
    @DisplayName("When getCarWashByQualification With Invalid Qualification Then Returns ResourceNotFoundException")
    public void whenGetCarWashByQualificationwithInvalidQualificationThenReturnsResourceNotFoundException() {
        //Arrange

        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "CarWash", "Qualification", 3);

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
        List<CarWash> carWashList = new ArrayList<>();

        when(carWashRepository.findCarWashByQualification(3))
                .thenReturn(carWashList);
        //Act

        Throwable exception = Assertions.catchThrowable(() -> {
            Page<CarWash> carWash = carWashService.findByQualification(3,pageable);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getCarWashByRangeQualification with Valid Qualification Then Returns CarWashes")
    public void whenGetCarWashByRangeQualificationwithValidQualificationThenReturnsCarWashes(){
        //Arrange
        String description = "Carros bien limpios";
        String name = "LimpiaAutos";
        String name_owner = "Marco Salas";
        int qualification = 3;
        int available = 2;

        CarWash carWash = new CarWash().setDescription(description).setName_owner(name_owner)
                .setName(name).setQualification(qualification).setAvailable(available);

        String description2 = "Carros bien limpios2";
        String name2 = "LimpiaAutos2";
        String name_owner2 = "Marco Salas2";
        int qualification2 = 3;
        int available2 = 2;

        CarWash carWash2 = new CarWash().setDescription(description2).setName_owner(name_owner2)
                .setName(name2).setQualification(qualification2).setAvailable(available2);

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
        List<CarWash> carWashList = new ArrayList<>();
        carWashList.add(carWash);
        carWashList.add(carWash2);
        when(carWashRepository.findByQualificationBetween(3,5))
                .thenReturn(carWashList);
        // Act
        Page<CarWash> foundedCarWashesByQualification = carWashService.findByQualificationRange(3,5,pageable);

        // Assert
        assertThat(foundedCarWashesByQualification.getTotalElements()).isEqualTo(2);

    }

    @Test
    @DisplayName("When getCarWashByRangeQualification with Invalid Qualification Then Returns ResourceNotFoundException")
    public void whenGetCarWashByRangeQualificationwithInvalidQualificationThenReturnsResourceNotFoundException() {
        //Arrange

        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "CarWash", "Qualification", 3);

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
        List<CarWash> carWashList = new ArrayList<>();

        when(carWashRepository.findByQualificationBetween(3,5))
                .thenReturn(carWashList);
        //Act

        Throwable exception = Assertions.catchThrowable(() -> {
            Page<CarWash> carWash = carWashService.findByQualification(3,pageable);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getCarWashByName With Invalid Name Then Returns CarWashes")
    public void whenGetCarWashByNameWithInvalidNameThenReturnsCarWashes(){
        //Arrange
        String description = "Carros bien limpios";
        String name = "LimpiaAutos";
        String name_owner = "Marco Salas";
        int qualification = 3;
        int available = 2;

        CarWash carWash = new CarWash().setDescription(description).setName_owner(name_owner)
                .setName(name).setQualification(qualification).setAvailable(available);

        String description2 = "Carros bien limpios2";
        String name2 = "Autos Limpios";
        String name_owner2 = "Marco Salas2";
        int qualification2 = 3;
        int available2 = 2;

        CarWash carWash2 = new CarWash().setDescription(description2).setName_owner(name_owner2)
                .setName(name2).setQualification(qualification2).setAvailable(available2);

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
        List<CarWash> carWashList = new ArrayList<>();
        carWashList.add(carWash);
        carWashList.add(carWash2);

        when(carWashRepository.findCarWashByNameContains("Limpi"))
                .thenReturn(carWashList);

        Page<CarWash> carWashPage = carWashService.getCarWashByName("Limpi", pageable);

        assertThat(carWashPage.getTotalElements()).isEqualTo(2);


    }

    @Test
    @DisplayName("When getCarWashByName With Valid Name Then Returns CarWashes")
    public void whenGetCarWashByNameWithValidNameThenReturnsCarWashes(){
        //Arrange
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "CarWash", "Name", "Limpi");

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
        List<CarWash> carWashList = new ArrayList<>();

        when(carWashRepository.findCarWashByQualification(3))
                .thenReturn(carWashList);
        //Act

        Throwable exception = Assertions.catchThrowable(() -> {
            Page<CarWash> carWash = carWashService.getCarWashByName("Limpi",pageable);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
