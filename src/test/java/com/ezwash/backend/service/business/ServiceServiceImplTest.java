package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Service;
import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.ServiceService;
import com.ezwash.backend.service.business.ServiceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ServiceServiceImplTest {
    @MockBean
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceService serviceService;

    @TestConfiguration
    static class ServiceServiceImplTestConfiguration{
        @Bean
        public ServiceService serviceService(){
            return new ServiceServiceImpl();
        }
    }

    @Test
    @DisplayName("when createService With Valid Attributes Then Returns Service")
    public void whenCreateServiceWithValidAttributesThenReturnsService(){
        // Arrange
        String name = "Encerado";
        String description = "Ofrecemos el servicio perfecto de encerado para que usted pueda obtener ";
        Integer is_promotion= 0;
        double price = 30.9;

        Service service = new Service()
                .setName(name)
                .setDescription(description)
                .setIs_promotion(is_promotion)
                .setPrice(price);

        String CarWash_description = "Somos el mejor CarWash de la historia";
        String CarWash_name = "Limpieza Total";
        String name_owner = "Carlos" ;
        Integer available = 1;
        Integer qualification = 0;
        Long carWashId = 1L;

        CarWash carWash = new CarWash()
                .setDescription(CarWash_description)
                .setName(CarWash_name)
                .setName_owner(name_owner)
                .setAvailable(available)
                .setQualification(qualification)
                .setId(carWashId);

        when(serviceRepository.save(service))
                .thenReturn(
                        new Service()
                        .setId(1L)
                        .setCarWash(carWash)
                        .setName(name)
                        .setDescription(description)
                        .setPrice(price)
                        .setIs_promotion(is_promotion)
                );
        // Act
        Service createdService = serviceService.createService(service);

        // Assert
        assertThat(createdService.getId()).isEqualTo(1L);
        assertThat(createdService.getCarWash().getId()).isEqualTo(carWashId);
    }
}
