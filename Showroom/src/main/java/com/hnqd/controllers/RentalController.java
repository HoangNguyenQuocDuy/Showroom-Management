/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.RentalRequest;
import com.hnqd.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/rentals")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createRental(@RequestBody RentalRequest rentalRequest,
            @RequestHeader("Authorization") String tokenHeader) {

        try {
            String token = tokenHeader.substring("Bearer ".length());

            rentalService.addRental(rentalRequest, token);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create rental successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create rental: " + e.getMessage()
            );
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getRentals(
            @RequestHeader("Authorization") String tokenHeader) {

        try {
            String token = tokenHeader.substring("Bearer ".length());

            return ResponseEntity.status(HttpStatus.OK).body(rentalService.getRentals(token)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get rentals " + e.getMessage()
            );
        }
    }
    
    @PatchMapping("/update/{rentalId}")
    public ResponseEntity<?> updateBooking(
            @RequestHeader("Authorization") String tokenHeader,
            @PathVariable("rentalId") int rentalId,
            @RequestBody String status
    ) {
        try {
            String token = tokenHeader.substring("Bearer ".length());
            
            rentalService.updateRentals(token, rentalId, status);
            return ResponseEntity.status(HttpStatus.OK).body("Rental updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when update rental " + e.getMessage()
            );
        }
    }
}
