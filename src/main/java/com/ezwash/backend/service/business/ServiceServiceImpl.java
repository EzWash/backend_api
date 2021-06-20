package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.ServiceService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public com.ezwash.backend.domain.model.business.Service getServiceById(Long id){
        return serviceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Service","Id",id));
    };

    public com.ezwash.backend.domain.model.business.Service updateService(Long carWashId, Long serviceId, com.ezwash.backend.domain.model.business.Service serviceInfo){
        if(!carWashRepository.existsById(carWashId))
            throw new ResourceNotFoundException("CarWash", "Id", carWashId);
        return serviceRepository.findById(serviceId).map(
                service -> {
                    service.setName(serviceInfo.getName())
                    .setDescription(serviceInfo.getDescription())
                    .setIs_promotion(serviceInfo.getIs_promotion())
                    .setPrice(serviceInfo.getPrice())
                    .setDetails(serviceInfo.getDetails());
                    return serviceRepository.save(service);
                }).orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public List<com.ezwash.backend.domain.model.business.Service> getServiceByCarWashId(Long carWashId){
        if(!carWashRepository.existsById(carWashId))
            throw new ResourceNotFoundException("CarWash", "Id", carWashId);
        List<com.ezwash.backend.domain.model.business.Service> serviceList =
                serviceRepository.listServiceByCarWashId(carWashId);
        if(serviceList.size() == 0)
            throw new ResourceNotFoundException("ServiceList", "Size", 0);
        return serviceRepository.listServiceByCarWashId(carWashId);
    }

}
