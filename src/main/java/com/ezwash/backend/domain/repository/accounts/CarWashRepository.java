package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.CarWash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarWashRepository extends JpaRepository<CarWash,Long> {

}
