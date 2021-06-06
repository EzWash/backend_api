package com.ezwash.backend.domain.model.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.accounts.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne services
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    //ManyToOne users
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "Varchar(50) default 'recojo'")
    private String state;

    //ManyToOne staff
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

    //OneToOne report
    @OneToOne(mappedBy = "contract")
    private Report report;

    //ManyToMany contract-service
    @ManyToMany
    @JoinTable(
            name = "contract_services",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;


    public Long getId() {
        return id;
    }

    public Contract setId(Long id) {
        this.id = id;
        return this;
    }

    public Service getService() {
        return service;
    }

    public Contract setService(Service service) {
        this.service = service;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Contract setUser(User user) {
        this.user = user;
        return this;
    }

    public String getState() {
        return state;
    }

    public Contract setState(String state) {
        this.state = state;
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

    public List<Service> getServices() {
        return services;
    }

    public Contract setServices(List<Service> services) {
        this.services = services;
        return this;
    }
}
