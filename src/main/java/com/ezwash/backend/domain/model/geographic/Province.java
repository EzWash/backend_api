package com.ezwash.backend.domain.model.geographic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    //OneToMany district
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
    @JsonIgnore
    private List<District> district;

    //ManyToOne department
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false, updatable = false)
    @JsonIgnore
    private Department department;

    public Long getId() {
        return id;
    }

    public Province setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Province setName(String name) {
        this.name = name;
        return this;
    }

    public List<District> getDistrict() {
        return district;
    }

    public Province setDistrict(List<District> district) {
        this.district = district;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Province setDepartment(Department department) {
        this.department = department;
        return this;
    }
}
