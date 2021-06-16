package com.ezwash.backend.domain.model.interactions;

import com.ezwash.backend.domain.model.accounts.CarWash;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car_carwashes")
public class CardCarWashes extends Card {
    @ManyToOne
    @JoinColumn(name = "carwash_id")
    private CarWash carWash;

    public CarWash getCarWash() {
        return carWash;
    }

    public CardCarWashes setCarWash(CarWash carWash) {
        this.carWash = carWash;
        return this;
    }
}
