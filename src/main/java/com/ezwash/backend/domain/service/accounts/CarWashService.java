package com.ezwash.backend.domain.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CarWashService {
    CarWash createCarWash(CarWash carWash, Location location);
    CarWash editCarWash(Long carwashId, CarWash carWashRequest, Location location);
    CarWash editQualification (Long carwashId, CarWash carWashRequest);
    CarWash findCarWashById(Long id);
    CarWash findCarWashByEmail(String email);
    Page<CarWash> getCarWashesLessThanDistance(double lt_1, double lg_1, double distance, Pageable pageable);
    Page<CarWash> findByQualificationRange(Integer qualification,Integer qualification2, Pageable pageable);
    Page<CarWash> findByQualification(Integer qualification, Pageable pageable);
    Page<Comment>getCarWashComments(Long id,Pageable pageable);
    Integer getCarWashQualification(Long id);
    Page<CarWash> getCarWashByName(String name, Pageable pageable);
    public List<CarWash> getAllCarWash();
}
