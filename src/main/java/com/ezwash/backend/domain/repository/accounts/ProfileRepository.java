package com.ezwash.backend.domain.repository.accounts;

import com.ezwash.backend.domain.model.accounts.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
