package com.ezwash.backend.controller.business;

import com.ezwash.backend.domain.model.business.Contract;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.repository.business.ContractRepository;
import com.ezwash.backend.domain.repository.business.ReportRepository;
import com.ezwash.backend.domain.service.business.ContractService;
import com.ezwash.backend.domain.service.business.ReportService;
import com.ezwash.backend.resource.business.ReportResource;
import com.ezwash.backend.resource.business.SaveReportResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReportsController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create Reports", description = "Create and return a Report", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/contracts/{contractId}/reports")
    public ReportResource createCriticalReport(@PathVariable Long contractId, @Valid @RequestBody SaveReportResource resource){
        Report report = convertToEntity(resource);
        return convertToResource(reportService.createCriticalReport(contractId, report));
    }

    private Report convertToEntity(SaveReportResource resource){
        return mapper.map(resource, Report.class);
    }

    private ReportResource convertToResource(Report report){
        return mapper.map(report, ReportResource.class);
    }
}
