package com.ezwash.backend.service.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.geographic.Location;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.service.accounts.CarWashService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarWashServiceImpl implements CarWashService {
    @Autowired
    private CarWashRepository carWashRepository;

    @Override
    public CarWash createCarWash(CarWash carWash, Location location) {
        carWash.setLocation(location);
        carWash.setAvailable(1);
        carWash.setQualification(0);
        return carWashRepository.save(carWash);
    }

    @Override
    public CarWash editCarWash(Long carwashId, CarWash carWashRequest, Location location){
        CarWash carwash = carWashRepository.findById(carwashId)
                .orElseThrow(() -> new ResourceNotFoundException("CarWash", "Id", carwashId));
        carwash.setDescription(carWashRequest.getDescription())
                .setName(carWashRequest.getName())
                .setName_owner(carWashRequest.getName_owner())
                .setLocation(location);

        return carWashRepository.save(carwash);
    }

    @Override
    public CarWash editQualification(Long carwashId,CarWash carWashRequest) {
        CarWash carwash = carWashRepository.findById(carwashId).orElseThrow(() ->new ResourceNotFoundException("CarWash", "Id", carwashId));
        carwash.setQualification(carWashRequest.getQualification());
        return carWashRepository.save(carwash);
    }

    public CarWash findCarWashById(Long id) {
        return carWashRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car Wash", "Id", id));
    }

    @Override
    public CarWash findCarWashByEmail(String email) {
        return carWashRepository.findCarWashByEmail(email);
    }

    @Override
    public Page<CarWash> getCarWashesLessThanDistance(double lt_1, double lg_1, double distance, Pageable pageable) {
        List<CarWash> carWashesNear = new ArrayList<>();
        Page<CarWash> carWashes = carWashRepository.findAll(pageable)
                .map(carWash -> {
                    double diff_distance = carWash.getLocation().getDistance(lt_1, lg_1);
                    if(diff_distance <= distance) {
                        carWashesNear.add(carWash);
                    }
                    return carWash;
                });
        if(carWashesNear.size() == 0){
            throw new ResourceNotFoundException("CarWash", "Distance", distance);
        }
        return new PageImpl<>(carWashesNear, pageable, carWashesNear.size());
    }

    @Override
    public Page<CarWash> findByQualification(Integer qualification, Pageable pageable){
        List<CarWash> carWashList = carWashRepository.findCarWashByQualification(qualification);
        if(carWashList.size()==0){
            throw new ResourceNotFoundException("CarWash", "Qualification", qualification);
        }
        else {
            return new PageImpl<>(carWashList,pageable,carWashList.size());
        }

    }

    @Override
    public Page<Comment> getCarWashComments(Long id, Pageable pageable) {
        CarWash carWash=findCarWashById(id);
        List<Comment>commentList=carWash.getCommentList();
        return new PageImpl<>(commentList,pageable,commentList.size());
    }

    @Override
    public Integer getCarWashQualification(Long id) {
        CarWash carWash=findCarWashById(id);
        return carWash.getQualification();
    }

    @Override
    public Page<CarWash> findByQualificationRange(Integer qualification1,Integer qualification2, Pageable pageable) {
        List<CarWash> carWashList = carWashRepository.findByQualificationBetween(qualification1, qualification2);
        if (carWashList.size() == 0) {
            throw new ResourceNotFoundException("CarWash", "Qualification", "Range");
        } else {
            return new PageImpl<>(carWashList, pageable, carWashList.size());
        }
    }

    @Override
    public Page<CarWash> getCarWashByName(String name, Pageable pageable){
        List<CarWash> carWashList = carWashRepository.findCarWashByNameContains(name);
        if (carWashList.size() == 0) {
            throw new ResourceNotFoundException("CarWash", "Name", name);
        } else {
            return new PageImpl<>(carWashList, pageable, carWashList.size());
        }
    }
}