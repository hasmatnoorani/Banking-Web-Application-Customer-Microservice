package com.stl.hasmatnoorani.bankingwebapplication.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @Column(name="customer_email")
    private String customerEmail;
    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_password")
    private String customerPassword;
    @Column(name="date_customer_joined")
    private Date dateCustomerJoined;

    public Customer() {
    }

    public Customer(String customerName, String customerEmail, String customerPassword, Date dateCustomerJoined) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.dateCustomerJoined = dateCustomerJoined;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public Date getDateCustomerJoined() {
        return dateCustomerJoined;
    }

    public void setDateCustomerJoined(Date dateCustomerJoined) {
        this.dateCustomerJoined = dateCustomerJoined;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerEmail='" + customerEmail + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", dateCustomerJoined=" + dateCustomerJoined +
                '}';
    }
}
