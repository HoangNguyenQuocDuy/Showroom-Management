/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import java.util.List;

/**
 *
 * @author DELL
 */
public interface ImageRepository {

    void addImage(int vehicleId, String imageUrl);

    void addImages(int vehicleId, List<String> imageUrls);

    void removeImage(int vehicleId, String imageUrl);

    void removeImages(int vehicleId, List<String> imageUrl);
}
