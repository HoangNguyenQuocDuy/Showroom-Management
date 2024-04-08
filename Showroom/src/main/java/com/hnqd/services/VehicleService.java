/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Image;
import com.hnqd.pojo.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author DELL
 */
public interface VehicleService {
    void addVehicle(Vehicle vehicle);

    List<VehicleResponse> getVehicles(Map<String, String> params);

    void deleteVehicleById(int id);

    VehicleResponse updateVehicleById(Map<String, String> params);

    VehicleResponse updateImagesVehicle(int vehicleId, Set<Image> images);

    VehicleResponse getVehicleById(int id);
}
