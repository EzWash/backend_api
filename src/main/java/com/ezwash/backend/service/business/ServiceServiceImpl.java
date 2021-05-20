package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public com.ezwash.backend.domain.model.business.Service createService(com.ezwash.backend.domain.model.business.Service service) {
        return serviceRepository.save(service);
    }
}
