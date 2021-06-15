package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.business.Report;

public interface ReportService {
    Report createCriticalReport(Long contractId, Report report);
    Report findReportById(Long Id);
}
