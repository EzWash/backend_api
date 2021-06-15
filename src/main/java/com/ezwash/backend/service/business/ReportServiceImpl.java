package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.repository.business.ReportRepository;
import com.ezwash.backend.domain.service.business.ReportService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report createCriticalReport(Long contractId, Report report){
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "Id", contractId));

        report.setContract(contract);
        return  reportRepository.save(report);
    }

    @Override
    public Report findReportById(Long id){
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "Id", id));
    }
}
