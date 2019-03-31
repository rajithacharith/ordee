package com.charith.ordee.controller;

import com.charith.ordee.beans.dto.LoginDTO;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.rest.HomeController;
import com.charith.ordee.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.mail.internet.ContentType;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
public class HomeControllerTest extends AbstractTest {


    private AuthService authService;
    @Before
    public void setup(){
        super.setUp();
    }
    @Test
    public void registerTest() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Charith");
        userDTO.setPassword("charith@96");
        userDTO.setName("Charith Rajitha");
        userDTO.setUserType("customer");
        userDTO.setUserAddress("Veyangoda");
        userDTO.setEmail("rajithacharith@gmail.com");
        userDTO.setUserTel("0702060670");
        String json = mapToJson(userDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }
    @Test
    public void loginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Charith");
        loginDTO.setPassword("charith@96");
        String json = mapToJson(loginDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }



}
