package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceService {
    Service createService(Service service);

    Service getServiceById(Long id);

    Service updateService(Long carWashId, Long serviceId, Service serviceInfo);

    List<Service> getServiceByCarWashId(Long carWashId);
    ResponseEntity<?> deleteServiceById (Long idService);


}
