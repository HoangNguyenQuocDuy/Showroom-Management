/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.services.CloudinaryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
@Controller
@RequestMapping("cloudinary")
public class CloudinaryUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            Map data = cloudinaryService.upload(file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    data
            );
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    e.getMessage()
            );
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<?> uploadMultipleImage(@RequestParam("images") MultipartFile[] files) {
        try {
            List<Map> data = cloudinaryService.uploadMultipleFile(files);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "upload sucessful " + data
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Something went wrong when upload images " + e.getMessage()
            );
        }
    }
}
