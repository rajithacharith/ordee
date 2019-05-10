package com.charith.ordee.beans.dto;

import java.util.List;

public class OrderListDTO {
    private String customerID;
    private List<OrderDTO> orderList;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
}
