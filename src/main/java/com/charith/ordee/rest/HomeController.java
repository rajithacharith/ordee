package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @PostMapping("login")
    public ResponseEntity login(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody UserDTO userDTO){

        return new ResponseEntity(HttpStatus.OK);
    }
}
