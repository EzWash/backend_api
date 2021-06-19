package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import com.ezwash.backend.service.accounts.StaffServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class StaffServiceImplTest {
    @MockBean
    private StaffRepository staffRepository;

    @MockBean
    private CarWashRepository carWashRepository;


    @Autowired
    private StaffService staffService;
    @TestConfiguration
    static class StaffServiceImplTestConfiguration{
        @Bean
        public StaffService staffService(){return new StaffServiceImpl(); }
    }
    @Test
    @DisplayName("When createStaff With Valid Attributes Then Returns Staff")
    public void whenCreateStaffWithValidAttributesThenReturnsStaff(){

        //Arrange
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";

        Long carwash_id=2L;
        CarWash carWash = new CarWash().setId(carwash_id);
        Staff staff=(Staff) new Staff().setCarWash(carWash)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);

        Mockito.when(staffRepository.save(staff)).thenReturn((Staff) new Staff()
        .setCarWash(carWash)
        .setFirst_name(first_name)
        .setLast_name(last_name)
        .setEmail(email)
        .setPhone_number(phone_number)
        .setGender(gender));
        //Act
        Staff createdStaff=staffService.createStaff(carWash, staff);
        //Assert
      assertThat(createdStaff.getFirst_name()).isEqualTo(first_name);
      assertThat(createdStaff.getCarWash()).isEqualTo(carWash);
    }

    @Test
    @DisplayName("When updateStaff With Valid Attributes Then Returns Staff")
    public void whenUpdateStaffWithValidAttributesThenReturnsStaff(){
        String first_name = "Roberto";
        String last_name = "Sanchez";
        String gender = "M";
        String email = "robertosan29@gmail.com";
        String phone_number = "987654321";
        Long staffId = 1L;


        String CarWash_description = "Somos el mejor CarWash de la historia";
        String CarWash_name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Integer available = 1;
        Integer qualification = 0;
        Long carWashId = 1L;


        List<Staff> staffList = new ArrayList<>();


        CarWash carWash = new CarWash()
                .setDescription(CarWash_description)
                .setName(CarWash_name)
                .setName_owner(name_owner)
                .setAvailable(available)
                .setQualification(qualification)
                .setId(carWashId)
                .setStaffList(staffList);

        Staff staff1 = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);



        carWash.getStaffList().add(staff1);


        Staff newStaff = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail("robertosan@gmail.com")
                .setPhone_number(phone_number)
                .setGender(gender);



        when(carWashRepository.existsById(carWashId)).thenReturn(true);

        when(staffRepository.findById(staffId)).thenReturn(Optional.ofNullable(staff1));

        when(staffRepository.save(staff1)).thenReturn(newStaff);



        Staff updatedStaff = staffService.updateStaff(carWashId, staffId, newStaff);

        assertThat(updatedStaff.getEmail()).isEqualTo("robertosan@gmail.com");

    }

    @Test
    @DisplayName("When updateStaff With Invalid CarWashId Then Returns ResourceNotFoundException")
    public void whenUpdateStaffWithInvalidCarWashIdThenReturnsResourceNotFoundException(){
        String template = "Resource %s not found for %s with value %s";


        String first_name = "Roberto";
        String last_name = "Sanchez";
        String gender = "M";
        String email = "robertosan29@gmail.com";
        String phone_number = "987654321";
        Long staffId = 1L;


        String CarWash_description = "Somos el mejor CarWash de la historia";
        String CarWash_name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Integer available = 1;
        Integer qualification = 0;
        Long carWashId = 1L;


        List<Staff> staffList = new ArrayList<>();


        CarWash carWash = new CarWash()
                .setDescription(CarWash_description)
                .setName(CarWash_name)
                .setName_owner(name_owner)
                .setAvailable(available)
                .setQualification(qualification)
                .setId(carWashId)
                .setStaffList(staffList);

        Staff staff1 = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);



        carWash.getStaffList().add(staff1);


        Staff newStaff = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail("robertosan@gmail.com")
                .setPhone_number(phone_number)
                .setGender(gender);



        when(carWashRepository.existsById(carWashId)).thenReturn(false);

        String expectedMessage  = String.format(template, "CarWash", "Id", carWashId);


        Throwable exception = catchThrowable(() ->{
            Staff updatedService = staffService.updateStaff(carWashId,staffId,newStaff);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    @DisplayName("When updateStaff With Invalid StaffId Then Returns ResourceNotFoundException")
    public void whenUpdateStaffWithInvalidStaffIdThenReturnsResourceNotFoundException(){
        String template = "Resource %s not found for %s with value %s";


        String first_name = "Roberto";
        String last_name = "Sanchez";
        String gender = "M";
        String email = "robertosan29@gmail.com";
        String phone_number = "987654321";
        Long staffId = 1L;


        String CarWash_description = "Somos el mejor CarWash de la historia";
        String CarWash_name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Integer available = 1;
        Integer qualification = 0;
        Long carWashId = 1L;


        List<Staff> staffList = new ArrayList<>();


        CarWash carWash = new CarWash()
                .setDescription(CarWash_description)
                .setName(CarWash_name)
                .setName_owner(name_owner)
                .setAvailable(available)
                .setQualification(qualification)
                .setId(carWashId)
                .setStaffList(staffList);

        Staff staff1 = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender);



        carWash.getStaffList().add(staff1);


        Staff newStaff = (Staff) new Staff()
                .setCarWash(carWash)
                .setId(staffId)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail("robertosan@gmail.com")
                .setPhone_number(phone_number)
                .setGender(gender);

        when(carWashRepository.existsById(carWashId)).thenReturn(true);

        when(staffRepository.findById(staffId)).thenReturn(Optional.empty());

        String expectedMessage  = String.format(template, "Staff", "Id", staffId);


        Throwable exception = catchThrowable(() ->{
            Staff updatedService = staffService.updateStaff(carWashId,staffId,newStaff);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
/*
    @Test
    @DisplayName ("When getStaffById With Valid CarWashId Then Returns Staff's List")
    public void whenGetStaffByIdWithValidCarWashIdThenReturnsStaff(){
        //Arrange
         //Creating CarWash
        CarWash carWash = new CarWash().setId(1L);

        //Creating Staff
        Staff staff= (Staff) new Staff().setId(1L);

        List<Staff> staff_carwash = new ArrayList<>();
        staff_carwash.add(staff);

        carWash.setStaffList(staff_carwash);



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

        when(carWashRepository.findById(1L))
                .thenReturn(Optional.of(carWash));

        // Act
        Page<Staff> staffPage = staffService.getStaffByCarWashId(1L, pageable);

        // Assert
        assertThat(staffPage.getTotalElements())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("When getStaffByCarWashId With Invalid CarWash Id Then Returns ResourceNotFoundException")
    public void whengetStaffByCarWashIdWithInvalidCarWashIdThenReturnsResourceNotFoundException(){
        // Arrange
        Long carWashId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Car Wash", "Id", carWashId);

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
        Throwable exception = Assertions.catchThrowable(() -> {
            Page<Staff> staffPage = staffService.getStaffByCarWashId(carWashId, pageable);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
    */
}
