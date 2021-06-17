package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.repository.accounts.StaffRepository;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.service.business.ContractService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import net.bytebuddy.TypeCache;
import org.apache.tomcat.util.http.parser.ContentRange;
import org.assertj.core.api.Assertions;
import org.assertj.core.error.ShouldBeAfterOrEqualTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class ContractServiceImplTest {
    @MockBean
    private ContractRepository contractRepository;

    @MockBean
    private StaffRepository staffRepository;

    @Autowired
    private ContractService contractService;

    @TestConfiguration
    static class ContractServiceImplTestConfiguration{
        @Bean
        public  ContractService contractService(){return new ContractServiceImpl();}
    }

    @Test
    @DisplayName("When designateStaffToContract With Valid Contract Id And Valid Staff Id Then Returns Contract")
    public void whenDesignateStaffToContractWithValidContractIdAndValidStaffIdThenReturnsContract(){
        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        Long staffId = 1L;
        List<Contract> staffList = new ArrayList<>();

        Staff staff = (Staff) new Staff()
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender("M")
                .setId(staffId);

        staff.setContractList(staffList);

        Long contractId = 1L;
        Contract contract  = new Contract()
                .setId(contractId);

        Contract contractUpdated = new Contract()
                .setId(contractId)
                .setStaff(staff);


        when(staffRepository.findById(staffId)).thenReturn(Optional.ofNullable(staff));

        when(contractRepository.findById(contractId)).thenReturn(Optional.ofNullable(contract));

        when(contractRepository.save(contract)).thenReturn(contractUpdated);

        Contract contractDesignated = contractService.designateStaffToContract(contractId, staffId);
        assertThat(contractDesignated.getStaff().getId()).isEqualTo(staffId);
        assertThat(contractDesignated.getStaff().getContractList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("When designateStaffToContract With Invalid Contract Id Then Returns ResourceNotFoundException")
    public void whenDesignateStaffToContractWithInvalidContractIdThenReturnsContractResourceNotFoundException(){
        Long staffId = 1L;
        Long contractId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Staff", "Id", staffId);

        when(staffRepository.findById(staffId)).thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable(() -> {
            Contract contract = contractService.designateStaffToContract(contractId,staffId);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
    @Test
    @DisplayName("When designateStaffToContract With Invalid Staff Id Then Returns ResourceNotFoundException")
    public void whenDesignateStaffToContractWithInvalidStaffIdThenReturnsContractResourceNotFoundException(){
        Long contractId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Contract", "Id", contractId);

        String first_name = "Mauricio Roe";
        String last_name = "Castillo Vega";
        String email = "era@gmail.com";
        String phone_number= "987655325";
        String gender = "M";
        Long staffId = 1L;
        List<Contract> staffList = new ArrayList<>();

        Staff staff = (Staff) new Staff()
                .setFirst_name(first_name)
                .setLast_name(last_name)
                .setEmail(email)
                .setPhone_number(phone_number)
                .setGender("M")
                .setId(staffId);

        staff.setContractList(staffList);


        when(staffRepository.findById(staffId)).thenReturn(Optional.ofNullable(staff));

        when(contractRepository.findById(contractId)).thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable(() -> {
            Contract contract = contractService.designateStaffToContract(contractId,staffId);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getContractsByState With Valid State And There Are Contracts With That State Then Returns Contracts")
    public void whenGetContractsByStateWithValidStateAndThereAreContractsWithThatStateThenReturnsContracts(){
        String state = "pending";

        Contract contract = new Contract()
                .setState(state);

        String state2 = "active";

        Contract contract2 = new Contract()
                .setState(state2);

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
        List<Contract> contractList = new ArrayList<>();
        contractList.add(contract);


        when(contractRepository.findContractByStateEquals("pending"))
                .thenReturn(contractList);


        Page<Contract> contractPage = contractService.getContractsByState("pending", pageable);

        assertThat(contractPage.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("When getContractsByState With Valid State And There Are No Contracts With That State Then Returns ResourceNotFoundException")
    public void whenGetContractsByStateWithValidStateAndThereAreNoContractsWithThatStateThenReturnsResourceFoundException(){
        String state = "pending";
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Contracts", "Found", 0);

        Contract contract = new Contract()
                .setState(state);

        Contract contract2 = new Contract()
                .setState(state);

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
        List<Contract> contractList = new ArrayList<>();


        when(contractRepository.findContractByStateEquals("pending"))
                .thenReturn(contractList);


        Throwable exception = Assertions.catchThrowable(() -> {
            Page<Contract> contractPage = contractService.getContractsByState(state, pageable);
        });

        //Assert

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getContractsByState With Invalid State Then Returns ResourceNotFoundException")
    public void whenGetContractsByStateWithInvalidStateThenReturnsResourceNotFoundException(){
        String state = "nose";
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "State", "Invalid", state);

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

        Throwable exception = Assertions.catchThrowable(() -> {
            Page<Contract> contractPage = contractService.getContractsByState(state, pageable);
        });

        //Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When updateContractState With Valid Arguments Then Returns Contract")
    public void whenUpdateContractStateWithValidArgumentsThenReturnsContract(){
       String state = "pending";
       String new_state = "active";
       Long contractId = 1L;

       Contract contract = new Contract()
               .setState(state)
               .setId(contractId);

       Contract newContract = new Contract()
               .setState(new_state)
               .setId(contractId);

       when(contractRepository.findById(contractId)).thenReturn(Optional.ofNullable(contract));

       when(contractRepository.save(contract)).thenReturn(newContract);

       Contract contractUpdated = contractService.updateContractState(contractId,new_state);

       assertThat(contractUpdated.getState()).isEqualTo(new_state);
    }

    @Test
    @DisplayName("When updateContractState With Invalid State And Contract State Is Not Finished Then Returns ResourceNotFoundException")
    public void whenUpdateContractStateWithInvalidStateAndContractStateIsNotFinishedThenReturnsResourceNotFoundException(){
       String state = "estadobueno";
       Long contractId = 1L;
       String template = "Resource %s not found for %s with value %s";
       String expectedMessage = String.format(template, "State", "Invalid", state);

       Throwable exception = Assertions.catchThrowable(() -> {
            Contract contractPage = contractService.updateContractState(contractId, state);
        });

        //Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When updateContractState With Invalid Contract Id Then Returns ResourceNotFoundException")
    public void whenUpdateContractStateWithInvalidContractIdThenReturnsResourceNotFoundException(){
       String state = "active";
       Long contractId = 1L;
       String template = "Resource %s not found for %s with value %s";
       String expectedMessage = String.format(template, "Contract", "Id", contractId);

       when(contractRepository.findById(contractId)).thenReturn(Optional.empty());

       Throwable exception = Assertions.catchThrowable(() -> {
            Contract contractPage = contractService.updateContractState(contractId, state);
        });

        //Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When updateContractState With Valid State And Contract State Is Finished Then Returns ResourceNotFoundException")
    public void whenUpdateContractStateWithValidStateAndContractStateIsFinishedThenReturnsResourceNotFoundException(){
       String state = "finished";
       String new_state = "active";
       Long contractId = 1L;
       String template = "Resource %s not found for %s with value %s";
       String expectedMessage = String.format(template, "Contract", "State", state);

       Contract contract = new Contract()
               .setState(state)
               .setId(contractId);

       when(contractRepository.findById(contractId)).thenReturn(Optional.ofNullable(contract));

       Throwable exception = Assertions.catchThrowable(() -> {
            Contract contractPage = contractService.updateContractState(contractId, new_state);
        });

        //Assert
        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
