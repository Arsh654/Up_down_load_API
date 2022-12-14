package com.buraak.orageServiceDB.Controller;

import com.buraak.orageServiceDB.Service.StorageServiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    private StorageServiceDB storageService;

    @GetMapping("")
    public String HelloController(){
        return "Hello From Controller";
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    //Request params should match the Request from the Postman as well.

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName){
        byte[] downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
