package com.charith.ordee.services;

import com.charith.ordee.beans.OrderBean;
import com.charith.ordee.beans.compositeKeys.OrderID;
import com.charith.ordee.beans.dto.OrderDTO;
import com.charith.ordee.beans.user.Chef;
import com.charith.ordee.beans.user.Merchant;
import com.charith.ordee.repository.ChefRepository;
import com.charith.ordee.repository.FoodItemRepository;
import com.charith.ordee.repository.MerchantRepository;
import com.charith.ordee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ChefRepository chefRepository;
    Logger logger = LogManager.getLogger();
    public ResponseEntity addOrder(OrderDTO orderDTO){
        List<String> foodItems = orderDTO.getFoodItemID();
        List<Integer> quantiy = orderDTO.getQuantity();
        HashMap<String,Integer> orderDetails = new HashMap<>();
        int orderID = (int) (orderRepository.count()+new Long(1));
        Chef chef = chefRepository.getChefByMerchantID(orderDTO.getMerchantID());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);




        for(int i = 0; i <= foodItems.size();i++){
            orderDetails.put(foodItems.get(i),quantiy.get(i));
            logger.info(foodItems.get(i)+" : "+quantiy.get(i));
            OrderID id = new OrderID(String.valueOf(orderID),foodItems.get(i));
            OrderBean orderBean = new OrderBean(id,orderDTO.getCustomerID(),orderDTO.getMerchantID(),foodItems.get(i),chef.getChefID(),quantiy.get(i),"Queued",date);
            orderRepository.save(orderBean);

        }

        //TODO:: Add observer observable to notify chef and merchants.
        return new ResponseEntity("Order added successfully",HttpStatus.OK);
    }

    public ResponseEntity getOrdersByOrderNo(String orderNo){
        OrderID orderID = new OrderID();
        orderID.setOrderID(orderNo);
        List orderItems = orderRepository.getAllByOrderID(orderID);
        logger.info(orderItems);
        return new ResponseEntity(orderItems,HttpStatus.OK);
    }

    public ResponseEntity cancleOrder(String orderNo,String foodItemID){
        OrderID orderID = new OrderID();
        orderID.setOrderID(orderNo);
        orderID.setFoodItemID(foodItemID);
        orderRepository.removeByOrderID(orderID);
        return new ResponseEntity("Order deleted successfully",HttpStatus.OK);
    }

    public ResponseEntity getOrderByCustomer(String customerID){
        orderRepository.getAllByCustomerID(customerID);
        return new ResponseEntity("",HttpStatus.OK);
    }


}
