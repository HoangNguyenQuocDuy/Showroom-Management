/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Image;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.VehicleRepository;
import com.hnqd.services.VehicleService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository VehicleRepository;

    @Override
    public void addVehicle(Vehicle vehicle) {
        VehicleRepository.addVehicle(vehicle);
    }

    @Override
    public void deleteVehicleById(int id) {
        VehicleRepository.deleteVehicleById(id);
    }

    @Override
    public VehicleResponse updateVehicleById(Map<String, String> params) {
        return VehicleRepository.updateVehicleById(params);
    }

    @Override
    public VehicleResponse getVehicleById(int id) {
        return VehicleRepository.getVehicleById(id);
    }

    @Override
    public List<VehicleResponse> getVehicles(Map<String, String> params) {
        return VehicleRepository.getVehicles(params);
    }

    @Override
    public VehicleResponse updateImagesVehicle(int i, Set<Image> set) {
        return VehicleRepository.updateImagesVehicle(i, set);
    }

}
