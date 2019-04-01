package com.charith.ordee.rest;

import com.charith.ordee.beans.compositeKeys.OrderID;
import com.charith.ordee.beans.dto.OrderDTO;
import com.charith.ordee.services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/customer/")
public class CustomerController {
    @Autowired
    private OrderService orderService;

    @PostMapping("addOrder")
    public ResponseEntity addOrder(@RequestBody OrderDTO orderDTO){
        System.out.println("Rest done");
        ResponseEntity responseEntity = orderService.addOrder(orderDTO);
        return responseEntity;
    }
    @GetMapping("cancleOrder")
    public ResponseEntity cancelOrder(@RequestParam("orderId") String orderID, @RequestParam("foodItemId") String foodItemID){
        System.out.println("Rest"+orderID+" "+foodItemID);
        ResponseEntity responseEntity = orderService.cancleOrder(orderID,foodItemID);
        return responseEntity;
    }

    @GetMapping("checkOrder")
    public ResponseEntity checkOrder(@RequestParam("orderId") String orderId){
        System.out.println("Check order");
        return orderService.checkOrder(orderId);
    }


    //TODO:: Check topselling food items

}
