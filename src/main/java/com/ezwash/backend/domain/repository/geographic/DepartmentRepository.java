package com.ezwash.backend.domain.repository.geographic;

import com.ezwash.backend.domain.model.geographic.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
