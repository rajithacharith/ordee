package com.charith.ordee;


import com.charith.ordee.services.RecomendationService;
import com.charith.ordee.services.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class OrdeeApplication implements CommandLineRunner {
    @Resource
    private StorageService storageService;
    @Resource
    private RecomendationService recomendationService;
    public static void main(String[] args) {

        SpringApplication.run(OrdeeApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
        recomendationService.mineRules();
    }

}
