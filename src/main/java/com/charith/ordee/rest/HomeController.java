package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class HomeController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity login(){

        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody UserDTO userDTO){
        ResponseEntity res = authService.register(userDTO);
        return res;
    }
}
