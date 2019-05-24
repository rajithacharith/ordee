package com.charith.ordee.beans;

import com.charith.ordee.beans.user.Customer;

public class OrderItem {
    private String orderID;
    private FoodItemBean foodItemBean;
    private Customer customer;
    private String status;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public FoodItemBean getFoodItemBean() {
        return foodItemBean;
    }

    public void setFoodItemBean(FoodItemBean foodItemBean) {
        this.foodItemBean = foodItemBean;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
