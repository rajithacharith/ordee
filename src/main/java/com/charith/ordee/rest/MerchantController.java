package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private FoodItemService foodItemService;
    @PostMapping("/addFoodItem")
    public ResponseEntity addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        foodItemService.addFoodItem(foodItemDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/changeFoodDetails")
    public ResponseEntity changeFoodDetails(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/addPhotos")
    public ResponseEntity addPhotos(){
        return new ResponseEntity("HelloWorld",HttpStatus.OK);
    }

    @GetMapping("/checkOrders")
    public ResponseEntity checkOrders(){
        return new ResponseEntity("HelloWorld",HttpStatus.OK);
    }
    @GetMapping("/topSellingFoods")
    public ResponseEntity topFoods(){
        return new ResponseEntity("HelloWorld",HttpStatus.OK);
    }
}
