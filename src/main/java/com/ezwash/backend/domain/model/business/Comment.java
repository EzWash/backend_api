package com.ezwash.backend.domain.model.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne carwashes
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carwash_id", nullable = false)
    @JsonIgnore
    private CarWash carWash;

    //ManyToOne users
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Lob
    @NotNull
    private String description;

    @NotNull
    private Integer qualification;

    @OneToOne(mappedBy = "comment")
    private Contract contract;

    public Customer getCustomer() {
        return customer;
    }

    public Comment setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Contract getContract() {
        return contract;
    }

    public Comment setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public Comment setCarWash(CarWash carWash) {
        this.carWash = carWash;
        return this;
    }

    public Customer getUser() {
        return customer;
    }

    public Comment setUser(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Comment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getQualification() {
        return qualification;
    }

    public Comment setQualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }
}
