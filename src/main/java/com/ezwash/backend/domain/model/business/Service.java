package com.ezwash.backend.domain.model.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(length = 100)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    @Lob
    private String details;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer is_promotion;

    @NotNull
    private Double price;

    //ManyToOne carwashes
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carwash_id", nullable = false)
    @JsonIgnore
    private CarWash carWash;

    //OneToMany contracts
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "contracts_services",
        joinColumns = @JoinColumn(name = "service_id"),
        inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    private List<Contract> contracts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public Service setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Service setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getIs_promotion() {
        return is_promotion;
    }

    public Service setIs_promotion(Integer is_promotion) {
        this.is_promotion = is_promotion;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Service setPrice(Double price) {
        this.price = price;
        return this;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public Service setCarWash(CarWash carWash) {
        this.carWash = carWash;
        return this;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public Service setContracts(List<Contract> contractList) {
        this.contracts = contractList;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Service setDetails(String details) {
        this.details = details;
        return this;
    }
}
