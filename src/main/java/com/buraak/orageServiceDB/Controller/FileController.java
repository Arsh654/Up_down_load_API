package com.buraak.orageServiceDB.Controller;

import com.buraak.orageServiceDB.Service.StorageServiceFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private static Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private StorageServiceFileSystem storageServiceFileSystem;

    @GetMapping("/file")
    public String FileCheck(){
        return "Testing FileController Flow";
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageFromFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
        log.info("Inside uploadImageFromFileSystem of File Controller");
        String uploadImage = storageServiceFileSystem.uploadImageToFileSystem(file);
        log.info(uploadImage);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    //Request params should match the Request from the Postman as well.

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable("fileName") String fileName) throws IOException {
        log.info("Inside downloadImageFromFileSystem of file Controller");
        byte[] downloadImage = storageServiceFileSystem.downloadImageFromFileSystem(fileName);
        log.info(String.valueOf(downloadImage));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
