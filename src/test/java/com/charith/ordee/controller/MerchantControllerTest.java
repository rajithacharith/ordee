package com.charith.ordee.controller;

import com.charith.ordee.beans.dto.FoodItemDTO;
import com.charith.ordee.beans.dto.LoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;

@ContextConfiguration
public class MerchantControllerTest extends AbstractTest {
    @Before
    public void setup(){
        super.setUp();
    }

    @Test
    public void addFoodItemTest() throws Exception {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setFoodName("Rice");
        foodItemDTO.setDescription("Bla bla bla");
        foodItemDTO.setPrice(200);
        foodItemDTO.setMerchantID("5001");
        String json = mapToJson(foodItemDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/merchant/addFoodItem")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/merchant/checkFoodItems")
                .param("merchantID","5001")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getOrderbyMerchantIDTest() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/merchant/checkOrders")
                .param("merchantID","5001")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
