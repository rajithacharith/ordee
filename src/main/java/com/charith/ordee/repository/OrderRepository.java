package com.charith.ordee.repository;

import com.charith.ordee.beans.OrderBean;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderBean,String> {
}
