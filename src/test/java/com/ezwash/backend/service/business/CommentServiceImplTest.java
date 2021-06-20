package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.business.CommentRepository;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.service.business.CommentService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CommentServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CarWashRepository carWashRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private ContractRepository contractRepository;

    @Autowired
    private CommentService commentService;

    @TestConfiguration
    static class CommentServiceImplTestConfiguration{
        @Bean
        public CommentService commentService(){return new CommentServiceImpl();}

    }

    @Test
    @DisplayName("When postComment With Valid Customer Id And Valid CarWash Id Then Returns Comment")
    public void whenPostCommentWithValidCustomerIdAndValidCarWashIdThenReturnsComment(){
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number = "987655325";
        String gender = "M";
        String password = "$3fsdg";
        Long customerId = 1L;

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

        Customer customer = (Customer) new Customer()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender)
                .setId(customerId);

        String CarWash_description = "Somos el mejor CarWash de la historia";
        String CarWash_name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Integer available = 1;
        Integer qualification = 0;
        Long carWashId = 1L;

        CarWash carWash = new CarWash()
                .setName(CarWash_name)
                .setDescription(CarWash_description)
                .setName_owner(name_owner)
                .setAvailable(available)
                .setQualification(qualification)
                .setLocation(location)
                .setId(carWashId);

        Comment comment = new Comment()
                .setDescription("Es un muy buen CarWash")
                .setQualification(5);

        Contract contract = new Contract();

        comment.setContract(contract);

        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        when(carWashRepository.findById(1L)).thenReturn(Optional.ofNullable(carWash));

        when(contractRepository.findById(1L)).thenReturn(Optional.ofNullable(contract));

        when(commentRepository.save(comment))
                .thenReturn(
                        new Comment()
                        .setId(1L)
                        .setCarWash(carWash)
                        .setUser(customer)
                        .setDescription(comment.getDescription())
                        .setQualification(comment.getQualification())
                        .setContract(comment.getContract())
                );

        Comment createdComment = commentService.postComment(customerId, carWashId,1L, comment);

        assertThat(createdComment.getId()).isEqualTo(1L);

    }


    @Test
    @DisplayName("When postComment With Invalid Customer Id Then Returns ResourceNotFoundException")
    public void whenPostCommentWithInvalidCustomerIdThenReturnsResourceNotFoundException(){
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Customer", "Id", 1L);

        Comment comment = new Comment()
                .setDescription("Es un muy buen CarWash")
                .setQualification(5);

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());


        Throwable exception = Assertions.catchThrowable(() ->{
            Comment createdComment = commentService.postComment(1L,1L, 1L, comment);
        });

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When postComment With Invalid CarWash Id Then Returns ResourceNotFoundException")
    public void whenPostCommentWithInvalidCarWashIdThenReturnsResourceNotFoundException(){
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "CarWash", "Id", 1L);

        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number = "987655325";
        String gender = "M";
        String password = "$3fsdg";
        Long customerId = 1L;

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

        Customer customer = (Customer) new Customer()
                .setPassword(password)
                .setLocation(location)
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender(gender)
                .setId(customerId);
        Comment comment = new Comment()
                .setDescription("Es un muy buen CarWash")
                .setQualification(5);

        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        when(carWashRepository.findById(1L)).thenReturn(Optional.empty());


        Throwable exception = Assertions.catchThrowable(() ->{
            Comment createdComment = commentService.postComment(1L,1L, 1L, comment);
        });

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}


