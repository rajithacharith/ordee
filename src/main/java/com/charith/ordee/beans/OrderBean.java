package com.charith.ordee.beans;


import com.charith.ordee.beans.compositeKeys.OrderID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Observable;

@Entity
public class OrderBean extends Observable {
    @Id
    private String orderID;
    private String customerID;
    private String merchantID;
    private String foodItemID;
    private String chefID;
    private String status;
    @JsonIgnore
    private Date date;

    public OrderBean(){

    }

    public OrderBean(String orderID, String customerID, String merchantID, String foodItemID, String chefID,  String status, Date date) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.merchantID = merchantID;
        this.foodItemID = foodItemID;
        this.chefID = chefID;

        this.status = status;
        this.date = date;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMerchatnID() {
        return merchantID;
    }

    public void setMerchatnID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getChefID() {
        return chefID;
    }

    public void setChefID(String chefID) {
        this.chefID = chefID;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setChanged();
        notifyObservers();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
