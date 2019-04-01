package com.charith.ordee.controller;

import com.charith.ordee.beans.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@ContextConfiguration
public class CustomerControllerTest extends AbstractTest {

    @Before
    public void setup(){
        super.setUp();
    }
    @Test
    public void addOrderTest() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        HashMap<String,Integer> data = new HashMap<>();
        data.put("1",4);
        data.put("2",5);
        orderDTO.setData(data);
        orderDTO.setCustomerID("1001");
        orderDTO.setMerchantID("3001");
        String json = mapToJson(orderDTO);
        System.out.println(json);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/customer/addOrder")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void cancleOrderTest() throws Exception{
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/customer/cancleOrder")
                .param("orderId","1").param("foodItemId","1")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());

    }
@Test
    public void checkOrderTest() throws Exception{
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/customer/checkOrder").param("orderId","1").param("foodItemId","2")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
