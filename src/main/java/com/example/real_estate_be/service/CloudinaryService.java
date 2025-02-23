package com.example.real_estate_be.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    //    public Map uploadImage(MultipartFile file) throws IOException {
//        // Tải ảnh lên Cloudinary
//        return  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//
//    }
//    public String uploadImage(MultipartFile file) throws IOException {
//        // Tải ảnh lên Cloudinary và lấy URL
//        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//        return (String) uploadResult.get("url"); // Lấy đường dẫn ảnh từ kết quả
//    }
    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "image",
                "secure", true  // Bật HTTPS
        ));
        return (String) uploadResult.get("secure_url");  // Lấy đường dẫn HTTPS
    }


    public Map uploadFile(File file) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    }

    public Map uploadFile(Byte file) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    }
}
