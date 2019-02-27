package com.charith.ordee.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @PostMapping("/addFoodItem")
    public ResponseEntity addFoodItem(){

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
