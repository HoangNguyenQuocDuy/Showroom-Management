/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.services.CloudinaryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
/**
 *
 * @author DELL
 */
@Service
public class CloudinaryServiceImpl implements CloudinaryService{

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public Map upload(MultipartFile file) {
        try {
            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", "Showroom"
            );

            return cloudinary.uploader().upload(file.getBytes(), params);
        } catch (IOException e) {
            throw new RuntimeException("Image loading failed!");
        }
    }

    @Override
    public List<Map> uploadMultipleFile(MultipartFile[] files) throws Exception {
        try {
            List<Map> result = new ArrayList<>();

            for (MultipartFile file: files) {
                Map image = upload(file);
                result.add(image);
            }

            return result;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
