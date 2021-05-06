package com.ezwash.backend.domain.model.accounts;

import com.ezwash.backend.domain.model.business.Contract;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="staff")
public class Staff extends Profile {

    //ManyToOne CarWash
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carwash_id", nullable = false)
    private CarWash carwash;

    //OneToMany contract
    @OneToMany(mappedBy = "contract_id")
    private List<Contract> contractList;

    //OneToMany user Roles

    public CarWash getCarwash() {
        return carwash;
    }

    public Staff setCarwash(CarWash carWash) {
        this.carwash = carWash;
        return this;
    }
}
