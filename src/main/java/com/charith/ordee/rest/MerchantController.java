package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.services.FoodItemService;
import com.charith.ordee.services.OrderService;
import com.charith.ordee.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        System.out.println(foodItemDTO.getFoodName());
        ResponseEntity response = foodItemService.addFoodItem(foodItemDTO);
        System.out.println(foodItemDTO.getImage());
        return response;
    }
    @PostMapping("/changeFoodDetails")
    public ResponseEntity changeFoodDetails(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping(value = "/addPhotos", consumes = "multipart/form-data") //TODO :: Add storage service
    public ResponseEntity addPhotos(@RequestPart("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        storageService.store(file);
        System.out.println("File Uploaded");

        return new ResponseEntity(HttpStatus.OK);


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
