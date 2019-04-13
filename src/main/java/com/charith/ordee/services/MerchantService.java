package com.charith.ordee.services;

import com.charith.ordee.beans.user.Merchant;
import com.charith.ordee.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public ResponseEntity getMerchants(){
        List<Merchant> merchantList = (List<Merchant>) merchantRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
        return new ResponseEntity(merchantList, httpHeaders,HttpStatus.OK);
    }
}
