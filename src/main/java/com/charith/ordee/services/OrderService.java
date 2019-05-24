package com.charith.ordee.services;

import com.charith.ordee.beans.OrderBean;
import com.charith.ordee.beans.OrderItem;
import com.charith.ordee.beans.compositeKeys.OrderID;
import com.charith.ordee.beans.dto.OrderDTO;
import com.charith.ordee.beans.dto.OrderListDTO;
import com.charith.ordee.beans.user.Chef;
import com.charith.ordee.beans.user.Merchant;
import com.charith.ordee.repository.*;
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
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private RecomendationService recomendationService;
    @Autowired
    private CustomerRepository customerRepository;

    Logger logger = LogManager.getLogger();
    public ResponseEntity addOrder(OrderListDTO orderListDTO){
        List<String> transaction = new ArrayList<String>();
        for (OrderDTO orderDTO:orderListDTO.getOrderList()) {
            int orderID = (int) (orderRepository.count()+new Long(1));
            Chef chef = chefRepository.getChefByMerchantID(orderDTO.getMerchantID());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String stringDate = dateFormat.format(date);
            System.out.println("Order adding");


            System.out.println(orderDTO.getFoodItemID());
            OrderBean orderBean = new OrderBean();
            orderBean.setDate(date);
            orderBean.setOrderID(Integer.toString(orderID));
            orderBean.setCustomerID(orderListDTO.getCustomerID());
            orderBean.setMerchantID(orderDTO.getMerchantID());
            orderBean.setFoodItemID(orderDTO.getFoodItemID());
            transaction.add(orderDTO.getFoodItemID());
            orderBean.setStatus("Queued");
            orderRepository.save(orderBean);

        }

        try {
            recomendationService.writeData(transaction);

        } catch (IOException e) {
            System.out.println(e.toString());
        }


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
        List<OrderBean> order = orderRepository.getAllByMerchantID(merchantID);
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderBean orderItem:order ) {
            OrderItem item = new OrderItem();
            item.setOrderID(orderItem.getOrderID());
            item.setFoodItemBean(foodItemRepository.getByFoodItemID(orderItem.getFoodItemID()));
            item.setCustomer(customerRepository.getCustomerByCustomerId(orderItem.getCustomerID()));
            item.setStatus(orderItem.getStatus());
            orderItemList.add(item);
        }
        return new ResponseEntity(orderItemList,HttpStatus.OK);
    }

    public ResponseEntity getRecomendation(OrderListDTO orderListDTO){
        Set itemSet = new HashSet<>();
        for (OrderDTO orderDTO:orderListDTO.getOrderList()
             ) {
            itemSet.add(orderDTO.getFoodItemID());



        }
        List data = recomendationService.getRecomendations(itemSet);
        return new ResponseEntity(data,HttpStatus.OK);
    }


}
