/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.dto.BookingRequest;
import com.hnqd.dto.UserResponse;
import com.hnqd.services.BookingService;
import com.hnqd.services.UserService;
import java.util.Map;
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
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest,
            @RequestHeader("Authorization") String token) {

        try {
            String username = jwtTokenUtil.extractUsername(token.substring("Bearer ".length()));

            bookingService.addBooking(bookingRequest, username);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create booking successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create booking " + e.getMessage()
            );
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<?> customerGetBookings(
            @RequestHeader("Authorization") String token) {

        try {
            Map<String, Object> userData = jwtTokenUtil.extractUserData(token.substring("Bearer ".length()));

            int customerId = (int) userData.get("userId");

            UserResponse user = userService.getUserById(customerId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Customer not found!"
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings(customerId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get customer's bookings " + e.getMessage()
            );
        }
    }

    @GetMapping("/customer/{bookingId}")
    public ResponseEntity<?> customerGetBookings(
            @PathVariable("bookingId") int bookingId,
            @RequestHeader("Authorization") String token) {

        try {
            Map<String, Object> userData = jwtTokenUtil.extractUserData(token.substring("Bearer ".length()));

            int customerId = (int) userData.get("userId");

            UserResponse user = userService.getUserById(customerId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Customer not found!"
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(bookingId, customerId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create booking " + e.getMessage()
            );
        }
    }

    @GetMapping("/staff")
    public ResponseEntity<?> staffGetBookings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create booking " + e.getMessage()
            );
        }
    }

    @PatchMapping("/update/{bookingId}")
    public ResponseEntity<?> updateBooking(
            @RequestHeader("Authorization") String token,
            @PathVariable("bookingId") int bookingId,
            @RequestBody String status
    ) {
        try {
            String username = jwtTokenUtil.extractUsername(token.substring("Bearer ".length()));
            // Thực hiện cập nhật booking và gửi email
            bookingService.updateBooking(username, bookingId, status);
            return ResponseEntity.status(HttpStatus.OK).body("Booking updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create booking " + e.getMessage()
            );
        }
    }
}
