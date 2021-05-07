package com.ezwash.backend;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.service.accounts.StaffService;
import com.ezwash.backend.service.accounts.StaffServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class StaffServiceImplTest {
    @MockBean
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @TestConfiguration
    static class StaffServiceImplTestConfiguration{
        @Bean
        public StaffService staffService(){return new StaffServiceImpl(); }
    }
    @Test
    @DisplayName("when createStaff With Valid Attributes Then Returns Staff")
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
        Staff createdStaff=staffService.createStaff(staff,carWash);
        //Assert
      assertThat(createdStaff.getFirst_name()).isEqualTo(first_name);
      assertThat(createdStaff.getCarWash()).isEqualTo(carWash);

    }


}
