package com.charith.ordee.beans.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class OrderID implements Serializable {
    @Column(name = "orderNo")
    private String orderID;
    @Column(name = "foodItemNo")
    private String foodItemID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        this.foodItemID = foodItemID;
    }
}
