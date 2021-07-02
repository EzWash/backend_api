package com.ezwash.backend.domain.model.business;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "services_carts",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> serviceList;

    public Customer getCustomer() {
        return customer;
    }

    public Cart setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public Cart setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public Cart addServiceToCart(Service service){
        if(!this.getServiceList().contains(service)) {
            this.getServiceList().add(service);
            return this;
        }else return null;
    }

    public Cart deleteServiceFromCart(Service service){
        if(this.getServiceList().contains(service)){
            this.getServiceList().remove(service);
            return this;
        }else return null;
    }
}
