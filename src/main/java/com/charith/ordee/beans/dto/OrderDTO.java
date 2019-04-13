package com.charith.ordee.beans.dto;


public class OrderDTO {


    private String customerID;
    private String merchantID;
    private String fooditemID;
    private int quantity;


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

    public String getFooditemID() {
        return fooditemID;
    }

    public void setFooditemID(String fooditemID) {
        this.fooditemID = fooditemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
