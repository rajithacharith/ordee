package com.charith.ordee.services;

import com.charith.ordee.beans.FoodItemBean;
import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.repository.FoodItemRepository;
import com.charith.ordee.services.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private StorageService storageService;
    public ResponseEntity addFoodItem(FoodItemDTO foodItemDTO){
        FoodItemBean foodItemBean = new FoodItemBean();
        int count = (int) foodItemRepository.count();
        String foodItemId = Integer.toString(count+1);
        foodItemBean.setFoodItemID(foodItemId);
        foodItemBean.setMerchantID(foodItemDTO.getMerchantID());
        foodItemBean.setFoodItemName(foodItemDTO.getFoodName());
        foodItemBean.setDescription(foodItemDTO.getDescription());
        foodItemBean.setPrice(foodItemDTO.getPrice());
        foodItemBean.setImage(foodItemDTO.getImage());
        foodItemRepository.save(foodItemBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity removeFoodItem(){
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity getFoodItemsByMerchant(String merchantID) throws IOException {
        List<FoodItemBean> foodItem = foodItemRepository.getAllByMerchantID(merchantID);


        return new ResponseEntity(foodItem,HttpStatus.OK);

    }
}
