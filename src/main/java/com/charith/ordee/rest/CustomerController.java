package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.OrderListDTO;
import com.charith.ordee.services.FoodItemService;
import com.charith.ordee.services.MerchantService;
import com.charith.ordee.services.OrderService;
import com.charith.ordee.services.RecomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/customer/")
public class CustomerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private RecomendationService recomendationService;
    @PostMapping("addOrder")
    public ResponseEntity addOrder(@RequestBody OrderListDTO orderlist){
        System.out.println("Rest done");
        ResponseEntity responseEntity = orderService.addOrder(orderlist);
        return responseEntity;
    }
    @GetMapping("cancleOrder")
    public ResponseEntity cancelOrder(@RequestParam("orderId") String orderID, @RequestParam("foodItemId") String foodItemID){
        System.out.println("Rest"+orderID+" "+foodItemID);
        ResponseEntity responseEntity = orderService.cancleOrder(orderID,foodItemID);
        return responseEntity;
    }
    @PostMapping("recommendation")
    public ResponseEntity recomendation(@RequestBody ArrayList<String> foodList){
        Set<String> foodSet = new HashSet<String>();
        for (String i:
             foodList) {
            foodSet.add(i);

        }
        System.out.println(foodList);
        List data = recomendationService.getRecomendations(foodSet);
        System.out.println(data);
        return new ResponseEntity(data,HttpStatus.OK);
    }

//    @GetMapping("checkOrder")
//    public ResponseEntity checkOrder(@RequestParam("orderId") String orderId){
//        System.out.println("Check order");
//        return orderService.checkOrder(orderId);
//    }


    //TODO:: Check topselling food items
    @GetMapping("getMerchants")
    public ResponseEntity getMerchants(){
        return merchantService.getMerchants();
    }
    @GetMapping("foodByMerchant")
    public ResponseEntity getFoodByMerchant(@RequestParam("merchantID") String merchantID){
        return foodItemService.getFoodItemsByMerchant(merchantID);
    }

}
