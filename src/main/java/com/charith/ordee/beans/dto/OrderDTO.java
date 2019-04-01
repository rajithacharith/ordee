package com.charith.ordee.beans.dto;

import java.util.HashMap;
import java.util.List;

public class OrderDTO {
    private HashMap<String,Integer> data;

    private String customerID;
    private String merchantID;



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


    public HashMap<String, Integer> getData() {
        return data;
    }

    public void setData(HashMap<String, Integer> data) {
        this.data = data;
    }
}
