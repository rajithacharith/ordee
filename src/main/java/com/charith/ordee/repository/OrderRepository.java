package com.charith.ordee.repository;

import com.charith.ordee.beans.OrderBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderBean,String> {
    OrderBean save(OrderBean orderBean);
    List getAllByChefID(String chefID);
}
