/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Image;
import com.hnqd.pojo.Vehicle;
import com.hnqd.services.VehicleService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/create/")
    public ResponseEntity createVehicle(@RequestBody Vehicle vehicle) {
        try {

            vehicleService.addVehicle(vehicle);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create Vehicle successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create vehicle " + e.getMessage()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllVehicles(@RequestParam Map<String, String> params) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    vehicleService.getVehicles(params)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get vehicles " + e.getMessage()
            );
        }
    }
    
    @GetMapping("/{vehicleId}")
    public ResponseEntity getVehicle(@PathVariable("vehicleId") int vehicleId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    vehicleService.getVehicleById(vehicleId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get vehicle by ID " + e.getMessage()
            );
        }
    }

    @PatchMapping("/update/")
    public ResponseEntity updateVehicleById(@RequestBody Map<String, String> params) {
        try {
            int vehicleId = Integer.parseInt(params.get("vehicleId"));

            VehicleResponse vehicleFind = vehicleService.getVehicleById(vehicleId);
            if (vehicleFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Vehicle not found!"
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    vehicleService.updateVehicleById(params)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when update vehicle " + e.getMessage()
            );
        }
    }
    
    @PatchMapping("/update/images/{vehicleId}")
    public ResponseEntity updateVehicleById(
            @PathVariable("vehicleId") int vehicleId,
            @RequestBody String[] images) {
        try {
            VehicleResponse vehicleFind = vehicleService.getVehicleById(vehicleId);
            if (vehicleFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Vehicle not found!"
                );
            }
            
            Set<Image> imageSet = new HashSet<>();
            for (String url : images) {

                Image image = new Image();
                image.setUrl(url);

                imageSet.add(image);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    vehicleService.updateImagesVehicle(vehicleId, imageSet)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when update vehicle " + e.getMessage()
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteImagesVehicleById(@PathVariable String id) {
        try {
            int vehicleId = Integer.parseInt(id);

            VehicleResponse vehicleFind = vehicleService.getVehicleById(vehicleId);
            if (vehicleFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Vehicle not found!"
                );
            }
            vehicleService.deleteVehicleById(vehicleId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    "Delete vehicle successfully"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when delete vehicle by id " + e.getMessage()
            );
        }
    }
}
