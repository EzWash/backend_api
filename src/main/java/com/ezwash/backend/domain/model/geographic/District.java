package com.ezwash.backend.domain.model.geographic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    //ManyToOne province
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "province_id", nullable = false, updatable = false)
    @JsonIgnore
    private Province province;

    //OneToMany location
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    @JsonIgnore
    private List<Location> location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
