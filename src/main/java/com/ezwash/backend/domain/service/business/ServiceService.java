package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    Service createService(Service service);

    Service getServiceById(Long id);

    Service updateService(Long carWashId, Long serviceId, Service serviceInfo);

    Page<Service> getServiceByCarWashId(Long carWashId, Pageable pageable);

}
