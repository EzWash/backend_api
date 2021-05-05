package com.ezwash.backend.domain.repository.geographic;

import com.ezwash.backend.domain.model.geographic.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
}
