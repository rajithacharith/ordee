package com.charith.ordee.services;

import com.charith.ordee.beans.dto.OrderDTO;
import com.charith.ordee.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodItemRepository foodItemRepository;

    public ResponseEntity addOrder(OrderDTO orderDTO){
        System.out.println(orderDTO.getFoodItemID());
        for(String i : orderDTO.getFoodItemID()){
            System.out.println("Food Item Id "+i);
        }
        return new ResponseEntity("Order added successfully",HttpStatus.OK);
    }
}
