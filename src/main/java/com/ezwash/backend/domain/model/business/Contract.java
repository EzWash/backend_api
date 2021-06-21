package com.ezwash.backend.domain.model.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "Varchar(50) default 'pending'")
    private String state;

    @NotNull
    private Date date;

    //ManyToOne staff
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

    //OneToOne report
    @OneToOne(mappedBy = "contract")
    private Report report;

    @ElementCollection
    private List<Long> servicesIds;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;


    @ManyToMany(mappedBy = "contracts")
    private List<Service> services = new ArrayList<>();


    public Contract setServicesIds(List<Long> servicesIds) {
        this.servicesIds = servicesIds;
        return this;
    }
    public Long getId() {
        return id;
    }

    public Contract setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Service> getServices() {
        return services;
    }

    public Contract setServices(List<Service> serviceList) {
        this.services = serviceList;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Contract setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Customer getUser() {
        return customer;
    }

    public Contract setUser(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getState() {
        return state;
    }

    public Contract setState(String state) {
        this.state = state;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Contract setDate(Date date) {
        this.date = date;
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public Contract setStaff(Staff staff) {
        this.staff = staff;
        return this;
    }

    public Report getReport() {
        return report;
    }

    public Contract setReport(Report report) {
        this.report = report;
        return this;
    }

    public List<Long> getServicesIds() {
        return servicesIds;
    }

    public Comment getComment() {
        return comment;
    }

    public Contract setComment(Comment comment) {
        this.comment = comment;
        return this;
    }
}
