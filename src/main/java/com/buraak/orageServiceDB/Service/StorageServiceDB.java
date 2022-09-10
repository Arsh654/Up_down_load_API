package com.buraak.orageServiceDB.Service;

import com.buraak.orageServiceDB.Entity.ImageData;
import com.buraak.orageServiceDB.Repository.StorageRepo;
import com.buraak.orageServiceDB.Util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceDB {

    @Autowired
    private StorageRepo storageRepo;

    //2 class :- upload and download

    public String uploadImage(MultipartFile file) throws IOException {
        //store the Image Data Object

        ImageData imageData = storageRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        //want to compress the image(Encode)  nd then upload it to the DB.
        //Encoding functionality is written in Utils package.

        if(imageData != null){
            return "File Uploaded Successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        //fetch the object with name as "fileName" from DB then decompressed it and return it to the user.
        Optional<ImageData> DBimageData = storageRepo.findByName(fileName);

        byte[] decompressImage = ImageUtils.decompressImage(DBimageData.get().getImageData());
        return decompressImage;

    }
}
