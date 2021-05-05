package com.ezwash.backend.domain.repository.interactions;

import com.ezwash.backend.domain.model.interactions.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
