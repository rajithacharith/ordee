package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.services.FoodItemService;
import com.charith.ordee.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/addFoodItem")
    public ResponseEntity addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        ResponseEntity response = foodItemService.addFoodItem(foodItemDTO);
        return response;
    }
    @PostMapping("/changeFoodDetails")
    public ResponseEntity changeFoodDetails(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/addPhotos") //TODO :: Add storage service
    public ResponseEntity addPhotos(){
        return new ResponseEntity("HelloWorld",HttpStatus.OK);
    }

    @GetMapping("/checkOrders")
    public ResponseEntity checkOrders(@RequestParam("merchantID") String merchantId){
        ResponseEntity response = orderService.getOrderByMerchantId(merchantId);
        return response;
    }
    @GetMapping("/topSellingFoods")
    public ResponseEntity topFoods(){
        return new ResponseEntity("HelloWorld",HttpStatus.OK);
    }
    @GetMapping("/checkFoodItems")
    public ResponseEntity foodItems(@RequestParam("merchantID") String merchantID){
        ResponseEntity response = foodItemService.getFoodItemsByMerchant(merchantID);
        return response;
    }
}
