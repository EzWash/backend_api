package com.ezwash.backend.domain.repository.interactions;

import com.ezwash.backend.domain.model.interactions.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
