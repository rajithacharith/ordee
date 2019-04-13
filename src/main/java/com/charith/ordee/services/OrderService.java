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
import org.hibernate.Session;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

        int orderID = (int) (orderRepository.count()+new Long(1));
        Chef chef = chefRepository.getChefByMerchantID(orderDTO.getMerchantID());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);
        System.out.println("Order adding");



        OrderBean orderBean = new OrderBean();
        orderBean.setDate(date);
        orderBean.setOrderID(Integer.toString(orderID));
        orderBean.setCustomerID(orderDTO.getCustomerID());
        orderBean.setMerchantID(orderDTO.getMerchantID());
        orderBean.setFoodItemID(orderDTO.getFooditemID());
        orderBean.setQuantity(orderDTO.getQuantity());
        orderBean.setStatus("Queued");
        orderRepository.save(orderBean);


        //TODO:: Add observer observable to notify chef and merchants.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain");
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity getOrdersByOrderNo(String orderNo){
        OrderID orderID = new OrderID();
        orderID.setOrderID(orderNo);
        List orderItems = orderRepository.getAllByOrderID(orderNo);
        logger.info(orderItems);
        return new ResponseEntity(orderItems,HttpStatus.OK);
    }

    public ResponseEntity cancleOrder(String orderNo,String foodItemID){

        System.out.println("Deleting");
        List<OrderBean> orderBeanList = orderRepository.getAllByOrderID(orderNo);

        for(OrderBean order : orderBeanList){
            orderRepository.delete(order);
        }
        return new ResponseEntity("Order deleted successfully",HttpStatus.OK);
    }

    public ResponseEntity getOrderByCustomer(String customerID){
        orderRepository.getAllByCustomerID(customerID);
        return new ResponseEntity("",HttpStatus.OK);
    }



    public ResponseEntity getOrderByMerchantId(String merchantID){
        List order = orderRepository.getAllByMerchantID(merchantID);
        return new ResponseEntity(order,HttpStatus.OK);
    }


}
