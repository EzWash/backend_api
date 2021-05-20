package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Service;

public interface ServiceService {
    Service createService(Service service);
    Service updateService(Long carWashId, Long serviceId, Service serviceInfo);
}
