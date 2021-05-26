package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarWashRepository extends JpaRepository<CarWash,Long> {
    @Query("SELECT c from CarWash c where c.qualification= ?1 ")
    public List<CarWash> findCarWashByQualification (Integer qualification);

}
