package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.business.Contract;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="staff")
public class Staff extends Profile {

    //ManyToOne CarWash
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carwash_id", nullable = false)
    @JsonIgnore
    private CarWash carWash;

    //OneToMany contract
    @OneToMany(mappedBy = "staff")
    private List<Contract> contractList;

    //OneToMany user Roles

    public CarWash getCarWash() {
        return carWash;
    }

    public Staff setCarWash(CarWash carWash) {
        this.carWash = carWash;
        return this;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public Staff setContractList(List<Contract> contractList) {
        this.contractList = contractList;
        return this;
    }
}
