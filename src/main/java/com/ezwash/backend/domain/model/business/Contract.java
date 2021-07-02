package com.ezwash.backend.domain.model.business;

import com.ezwash.backend.domain.model.AuditModel;
import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="contracts")
public class Contract extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "carwash_id", nullable = false)
    private CarWash carWash;


    @Column(columnDefinition = "Varchar(50) default 'pending'")
    private String state;


    @CreatedDate
    private Date date;

    //ManyToOne staff
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = true)
    private Staff staff;

    //OneToOne report
    @OneToOne(mappedBy = "contract")
    private Report report;

    @ElementCollection
    private List<Long> servicesIds;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToMany
    @JoinTable(name = "services_contracts",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> serviceContracts;

    private Double total;

    public Contract setServicesIds(List<Long> servicesIds) {
        this.servicesIds = servicesIds;
        return this;
    }
    public Long getId() {
        return id;
    }

    public Contract setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Service> getServiceContracts() {
        return serviceContracts;
    }

    public Contract setServiceContracts(List<Service> serviceContracts) {
        this.serviceContracts = serviceContracts;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Contract setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Customer getUser() {
        return customer;
    }

    public Contract setUser(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getState() {
        return state;
    }

    public Contract setState(String state) {
        this.state = state;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Contract setDate(Date date) {
        this.date = date;
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

    public List<Long> getServicesIds() {
        return servicesIds;
    }

    public Comment getComment() {
        return comment;
    }

    public Contract setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public Contract setCarWash(CarWash carWash) {
        this.carWash = carWash;
        return this;
    }

    public Contract addServiceToContract(Service service){
        if(!this.getServiceContracts().contains(service)) {
            this.getServiceContracts().add(service);
            return this;
        }else return null;
    }

    public Double getTotal() {
        return total;
    }

    public Contract setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Contract deleteServiceFromContract(Service service){
        if(this.getServiceContracts().contains(service)){
            this.getServiceContracts().remove(service);
            return this;
        }else return null;
    }

    public void updateTotal(){
        Double sum = 0.0;
        for (Integer i = 0; i < getServiceContracts().size(); i++){
            sum += getServiceContracts().get(i).getPrice();
        }

        setTotal(sum);
    }
}
