package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByFirst_name(String first_name);
}
