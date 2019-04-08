package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.services.FoodItemService;
import com.charith.ordee.services.OrderService;
import com.charith.ordee.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private StorageService storageService;
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
    @PostMapping(value = "/addPhotos", consumes = "multipart/form-data") //TODO :: Add storage service
    public ResponseEntity addPhotos(@RequestParam("file") MultipartFile file){
        String message = "";
        try {

            storageService.store(file);


            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }



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
