package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.repository.business.ReportRepository;
import com.ezwash.backend.domain.service.business.ReportService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import java.sql.Date;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ReportServiceImplTest {
    @MockBean
    private ContractRepository contractRepository;

    @MockBean
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    @TestConfiguration
    static class ReportServiceImplTestConfiguration{
        @Bean
        public ReportService reportService(){ return new ReportServiceImpl();}
    }

    @Test
    @DisplayName("When createCriticalReport With Valid Contract Id Then Returns Report")
    public void whenCreatedCriticalReportWithValidContractIdThenReturnsReport(){
        String state = "washing";

        Contract contract = new Contract()
                .setId(1L)
                .setState(state);


        String report_Description = "Rayaron mi coche";

        Report report  = new Report()
                .setId(1L)
                .setContract(contract)
                .setDescription(report_Description);

        when(contractRepository.findById(1L)).thenReturn(Optional.ofNullable(contract));

        when(reportRepository.save(report)).thenReturn(
                new Report()
                .setId(1L)
                .setContract(contract)
                .setDescription(report_Description)
        );

        Report reportSaved = reportService.createCriticalReport(1L, report);

        assertThat(reportSaved.getId()).isEqualTo(1L);

    }

    @Test
    @DisplayName("When createCriticalReport With Invalid Contract Id Then Returns Report")
    public void whenCreatedCriticalReportWithInvalidContractIdThenReturnsReport(){

        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Contract", "Id", 1L);

        String state = "washing";

        Contract contract = new Contract()
                .setId(1L)
                .setState(state);


        String report_Description = "Rayaron mi coche";

        Report report  = new Report()
                .setId(1L)
                .setContract(contract)
                .setDescription(report_Description);

        when(contractRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable(() ->{
          Report reportCreated = reportService.createCriticalReport(1L, report);
        });

        Assertions.assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
