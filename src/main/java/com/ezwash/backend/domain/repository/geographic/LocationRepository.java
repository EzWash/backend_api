package com.ezwash.backend.domain.repository.geographic;

import com.ezwash.backend.domain.model.geographic.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
