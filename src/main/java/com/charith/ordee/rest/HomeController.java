package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.LoginDTO;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.services.AuthService;
import com.charith.ordee.services.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Path;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class HomeController {
    @Autowired
    private AuthService authService;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.getUsername());
        ResponseEntity res = authService.login(loginDTO);
        return res;
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody UserDTO userDTO){
        ResponseEntity res = authService.register(userDTO);
        return res;
    }
    @GetMapping("getImage")
    public ResponseEntity getImage(@RequestParam("image") String image){
        Path path = fileSystemStorageService.load(image);
        File file = path.toFile();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+file.getName());
        return new ResponseEntity(file,headers,HttpStatus.OK);
    }
}
