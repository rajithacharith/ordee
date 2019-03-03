package com.charith.ordee.beans;


import com.charith.ordee.beans.compositeKeys.OrderID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;
@Entity
public class OrderBean {
    @EmbeddedId
    private OrderID orderID;
    private String customerID;
    private String merchatnID;
    private String foodItemID;
    private String chefID;
    private int quantity;
    private String status;
    @JsonIgnore
    private Date date;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMerchatnID() {
        return merchatnID;
    }

    public void setMerchatnID(String merchatnID) {
        this.merchatnID = merchatnID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
