package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.ServiceService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CarWashRepository carWashRepository;

    @Override
    public com.ezwash.backend.domain.model.business.Service createService(com.ezwash.backend.domain.model.business.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public com.ezwash.backend.domain.model.business.Service updateService(Long carWashId, Long serviceId, com.ezwash.backend.domain.model.business.Service serviceInfo){
        if(!carWashRepository.existsById(carWashId))
            throw new ResourceNotFoundException("CarWash", "Id", carWashId);
        return serviceRepository.findById(serviceId).map(
                service -> {
                    service.setName(serviceInfo.getName())
                    .setDescription(serviceInfo.getDescription())
                    .setIs_promotion(serviceInfo.getIs_promotion())
                    .setPrice(serviceInfo.getPrice());
                    return serviceRepository.save(service);
                }).orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
    }
}
