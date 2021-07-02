package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
