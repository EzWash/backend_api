package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
