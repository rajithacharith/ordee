package com.charith.ordee.repository;

import com.charith.ordee.beans.FoodItemBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodItemRepository extends CrudRepository<FoodItemBean,String> {
    FoodItemBean save(FoodItemBean foodItemBean);
    FoodItemBean getByFoodItemID(String foodItemID);
    List getAllByMerchantID(String merchantID);
}
