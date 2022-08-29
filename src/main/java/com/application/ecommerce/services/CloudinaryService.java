package com.application.ecommerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class CloudinaryService {

    public String uploadFile(MultipartFile gifFile) {
        try {
            MultipartFile gif = null;
            File uploadedFile = convertMultiPartToFile(gif);
            Cloudinary cloudinaryConfig = new Cloudinary();
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean deleted = uploadedFile.delete();

            if (deleted) {
                System.out.println("File u fshi me sukses.");
            } else
                System.out.println("File nuk ekziston.");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public void saveGifToDB(String url, String title, String currentUser) {

        }
    }