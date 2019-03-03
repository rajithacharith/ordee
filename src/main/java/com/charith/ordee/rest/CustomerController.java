package com.charith.ordee.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/customer/")
public class CustomerController {


    public ResponseEntity addOrder(){
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity cancelOrder(){
        return new ResponseEntity(HttpStatus.OK);
    }

}
