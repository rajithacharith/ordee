package com.charith.ordee.rest;

import com.charith.ordee.beans.compositeKeys.OrderID;
import com.charith.ordee.beans.dto.OrderDTO;
import com.charith.ordee.services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/customer/")
public class CustomerController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public ResponseEntity addOrder(@RequestBody OrderDTO orderDTO){

        return orderService.addOrder(orderDTO);
    }

    public ResponseEntity cancelOrder(@RequestBody String orderID,String foodItemID){
        ResponseEntity responseEntity = orderService.cancleOrder(orderID,foodItemID);
        return responseEntity;
    }

}
