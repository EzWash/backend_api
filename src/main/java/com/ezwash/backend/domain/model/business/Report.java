package com.ezwash.backend.domain.model.business;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //OneToOne contract
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contracts_id", nullable = false)
    private Contract contract;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public Report setId(Long id) {
        this.id = id;
        return this;
    }

    public Contract getContract() {
        return contract;
    }

    public Report setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Report setDescription(String description) {
        this.description = description;
        return this;
    }
}
