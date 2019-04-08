package com.charith.ordee;

import com.charith.ordee.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class OrdeeApplication implements CommandLineRunner {
    @Resource
    StorageService storageService;

    public static void main(String[] args) {

        SpringApplication.run(OrdeeApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }

}
