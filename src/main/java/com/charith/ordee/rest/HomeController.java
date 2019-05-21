package com.charith.ordee.rest;

import com.charith.ordee.beans.dto.LoginDTO;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.services.AuthService;
import com.charith.ordee.services.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<Resource> getImage(@RequestParam("image") String image) throws IOException {
        Path path = fileSystemStorageService.load(image);
        ResponseEntity responseEntity;
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() || resource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename="+resource.getFilename());
            headers.add("Content-Type","applicatin/jpg");
            headers.add("Content-Length", String.valueOf(resource.getFile().length()));
            responseEntity = new ResponseEntity(resource,headers,HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            throw new RuntimeException("FAIL!");
        }



        return responseEntity;
    }

    @RequestMapping("/file/{name}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("name") String name) throws IOException {
        String filename = fileSystemStorageService.load(name).toString();
        System.out.println(filename);
        InputStream inputImage = new FileInputStream(filename);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int l = inputImage.read(buffer);
        while(l >= 0) {
            outputStream.write(buffer, 0, l);
            l = inputImage.read(buffer);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/jpg");
        headers.set("Content-Disposition", "attachment; filename=\"" + name + ".jpg\"");
        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
