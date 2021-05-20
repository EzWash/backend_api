package com.ezwash.backend.domain.repository.interactions;

import com.ezwash.backend.domain.model.interactions.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
