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
