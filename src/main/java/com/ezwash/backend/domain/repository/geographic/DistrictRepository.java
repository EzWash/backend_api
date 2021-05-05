package com.ezwash.backend.domain.repository.geographic;

import com.ezwash.backend.domain.model.geographic.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District,Long> {
}
