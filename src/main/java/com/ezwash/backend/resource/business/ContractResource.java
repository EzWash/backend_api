package com.ezwash.backend.resource.business;

import com.ezwash.backend.domain.model.business.Report;

import java.util.Date;

public class ContractResource {

    private Long service_id;
    private Long user_id;
    private Date date;
    private Long staff_id;
    private Report report;


    public Long getService_id() {
        return service_id;
    }

    public ContractResource setService_id(Long service_id) {
        this.service_id = service_id;
        return this;
    }

    public Long getUser_id() {
        return user_id;
    }

    public ContractResource setUser_id(Long user_id) {
        this.user_id = user_id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public ContractResource setDate(Date date) {
        this.date = date;
        return this;
    }

    public Long getStaff_id() {
        return staff_id;
    }

    public ContractResource setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
        return this;
    }

    public Report getReport() {
        return report;
    }

    public ContractResource setReport(Report report) {
        this.report = report;
        return this;
    }
}
