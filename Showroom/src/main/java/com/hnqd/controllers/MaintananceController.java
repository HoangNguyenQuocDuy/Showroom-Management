/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.MaintenanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hnqd.services.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/maintenances")
public class MaintananceController {
    
    @Autowired
    private MaintenanceService maintananceService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createMaintanance(@RequestBody MaintenanceRequest maintananceRequest,
            @RequestHeader("Authorization") String tokenHeader) {

        try {
            String token = tokenHeader.substring("Bearer ".length());

            maintananceService.addMaintenance(maintananceRequest, token);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create maintenance successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create maintenance " + e.getMessage()
            );
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getMaintenances(
            @RequestHeader("Authorization") String tokenHeader) {

        try {
            String token = tokenHeader.substring("Bearer ".length());

            return ResponseEntity.status(HttpStatus.OK).body(maintananceService.getMaintenances(token)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get Maintenances " + e.getMessage()
            );
        }
    }
    
    @PatchMapping("/update/{maintenanceId}")
    public ResponseEntity<?> updateMaintenances(
            @RequestHeader("Authorization") String tokenHeader,
            @PathVariable("maintenanceId") int bookingId,
            @RequestBody String status
    ) {
        try {
            String token = tokenHeader.substring("Bearer ".length());
            
            maintananceService.updateMaintenance(token, bookingId, status);
            return ResponseEntity.status(HttpStatus.OK).body("Maintenance updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when update maintenance " + e.getMessage()
            );
        }
    }
}
