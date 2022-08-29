package com.application.ecommerce.controllers;

import com.application.ecommerce.services.CloudinaryService;
import com.application.ecommerce.services.UsersService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;
    private final UsersService usersService;

    public CloudinaryController(CloudinaryService cloudinaryService, UsersService usersService) {
        this.cloudinaryService = cloudinaryService;
        this.usersService = usersService;
    }

    @PostMapping("/gifs")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadGif(@RequestParam("gifFile") MultipartFile gifFile, Authentication authentication, @RequestParam("title") String title) throws IOException {

        String url = cloudinaryService.uploadFile(gifFile);
        String currentUser = "";
        cloudinaryService.saveGifToDB(url, title , currentUser);

        LinkedHashMap<String, Object> jsonResponse = new LinkedHashMap<>();
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }

}
