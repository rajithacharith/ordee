package com.charith.ordee.beans.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Observable;
import java.util.Observer;

@Entity
public class Customer implements Observer {
    @Id
    private String customerId;
    private String customerName;
    private String address;
    private String telephoneNo;


    public Customer(){

    }
    public Customer(String customerId, String customerName, String address, String telephoneNo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.telephoneNo = telephoneNo;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
