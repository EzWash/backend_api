package com.ezwash.backend.resource.business;

import com.ezwash.backend.domain.model.business.Report;

import javax.swing.plaf.nimbus.State;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class SaveContractResource {
    @NotNull
    private Long service_id;
    @NotNull
    private Long customer_id;
    @NotNull
    private Date date;
    @NotNull
    private Long staff_id;
    @NotNull
    private Report report;
    private String state;

    public String getState() {
        return state;
    }

    public SaveContractResource setState(String state) {
        this.state = state;
        return this;
    }

    public Long getService_id() {
        return service_id;
    }

    public SaveContractResource setService_id(Long service_id) {
        this.service_id = service_id;
        return this;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public SaveContractResource setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SaveContractResource setDate(Date date) {
        this.date = date;
        return this;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public SaveContractResource setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
        return this;
    }

    public Report getReport() {
        return report;
    }

    public SaveContractResource setReport(Report report) {
        this.report = report;
        return this;
    }
}
