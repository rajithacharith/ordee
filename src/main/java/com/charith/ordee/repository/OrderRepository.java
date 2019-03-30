package com.charith.ordee.repository;

import com.charith.ordee.beans.OrderBean;
import com.charith.ordee.beans.compositeKeys.OrderID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderBean,String> {
    OrderBean save(OrderBean orderBean);
    List getAllByChefID(String chefID);
    List getAllByOrderID(OrderID orderID);
    void removeByOrderID(OrderID orderID);
    List getAllByCustomerID(String customerID);
}
