package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
}
