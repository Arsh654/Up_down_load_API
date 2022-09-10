package com.buraak.orageServiceDB.Service;

import com.buraak.orageServiceDB.Controller.FileController;
import com.buraak.orageServiceDB.Entity.FileData;
import com.buraak.orageServiceDB.Entity.ImageData;
import com.buraak.orageServiceDB.Repository.FileDataRepository;
import com.buraak.orageServiceDB.Util.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageServiceFileSystem {

    @Autowired
    private FileDataRepository fileDataRepository;

    private static Logger log = LoggerFactory.getLogger(StorageServiceFileSystem.class);

    private String FOLDER_PATH = "C:\\Users\\asus\\Documents\\FileSystem";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        log.info("inside Service layer uploadImageToFileSystem METHOD of File System");

        String filePath = FOLDER_PATH + "\\" + file.getOriginalFilename();
        log.info(filePath);

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());
        log.info(String.valueOf(fileData));

        //transferring the file that needs to be uploaded to actual file System.
        file.transferTo(new File(filePath));

        if(fileData != null){
            return "File uploaded Successfully: " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        //fetch the object with name as "fileName" from DB then decompressed it and return it to the user.
        log.info("inside Service layer downloadImageFromFileSystem METHOD of File System");

        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        log.info(String.valueOf(fileData));
        String filePath = fileData.get().getFilePath();
        log.info(filePath);

        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        log.info(String.valueOf(images));
        return images;
    }
}
