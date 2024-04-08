/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
public interface CloudinaryService {
    Map upload(MultipartFile file);
    List<Map> uploadMultipleFile(MultipartFile[] files) throws Exception;
}
