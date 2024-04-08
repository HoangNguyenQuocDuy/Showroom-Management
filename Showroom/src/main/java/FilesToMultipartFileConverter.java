///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.hnqd.dto;
//
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.util.FileCopyUtils;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// *
// * @author DELL
// */
//public class FilesToMultipartFileConverter {
//    public static MultipartFile[] convert(String[] filePaths) throws IOException {
//        MultipartFile[] multipartFiles = new MultipartFile[filePaths.length];
//        for (int i = 0; i < filePaths.length; i++) {
//            String filePath = filePaths[i];
//            File file = new File(filePath);
//            byte[] fileContent = Files.readAllBytes(file.toPath());
//            String fileName = file.getName();
//            String originalFileName = fileName.substring(0, fileName.lastIndexOf('.'));
//            multipartFiles[i] = new MockMultipartFile(originalFileName, fileName, Files.probeContentType(Paths.get(filePath)), new FileInputStream(file));
//        }
//        return multipartFiles;
//    }
//
//     public static void main(String[] args) {
//        // Danh sách đường dẫn tới các file ảnh
//        String[] filePaths = {"/path/to/image1.jpg", "/path/to/image2.png", "/path/to/image3.gif"};
//
//        try {
//            // Chuyển đổi các file ảnh thành mảng MultipartFile
//            MultipartFile[] multipartFiles = convert(filePaths);
//
//            // Kiểm tra và in ra thông tin của từng MultipartFile
//            for (MultipartFile file : multipartFiles) {
//                System.out.println("File name: " + file.getName());
//                System.out.println("Original filename: " + file.getOriginalFilename());
//                System.out.println("Content type: " + file.getContentType());
//                System.out.println("Size: " + file.getSize());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
