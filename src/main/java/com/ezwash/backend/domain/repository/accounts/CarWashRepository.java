package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.sun.el.parser.AstGreaterThan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarWashRepository extends JpaRepository<CarWash,Long> {
    @Query("SELECT c from CarWash c where c.qualification= ?1 ")
    public List<CarWash> findCarWashByQualification (Integer qualification);

    public List<CarWash>findByQualificationBetween(Integer q1,Integer q2);

    public List<CarWash>findCarWashByNameContains(String name);

    CarWash findCarWashByEmail(String Email);
}
