package com.titova.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "LINK")
public class Link {
    
    private int id;
    private int customerId;
    private int insuranceId;
    private int userId;
    
    public Link() {
        id = 0;
        customerId = 0;
        insuranceId = 0;
        userId = 0;
    }
    
    public Link(int customerId, int softwareId, int userId){
        id = 0;
        this.customerId = customerId;
        this.insuranceId = softwareId;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CUSTOMER_ID")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(name = "INSURANCE_ID")
    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }
    
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}