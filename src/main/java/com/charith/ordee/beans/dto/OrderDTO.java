package com.charith.ordee.beans.dto;

import java.util.List;

public class OrderDTO {
    private List<String> foodItemID;
    private String customerID;
    private String merchantID;
    private List<Integer> quantity;

    public List<String> getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(List<String> foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
