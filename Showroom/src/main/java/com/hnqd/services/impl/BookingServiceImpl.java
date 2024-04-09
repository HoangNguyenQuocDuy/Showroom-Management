/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.dto.BookingRequest;
import com.hnqd.dto.BookingResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Booking;
import com.hnqd.repositories.BookingRepository;
import com.hnqd.services.BookingService;
import com.hnqd.services.EmailService;
import com.hnqd.services.UserService;
import com.hnqd.services.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private EmailService emailService;


    @Override
    public void addBooking(BookingRequest bookingRequest, String username) throws Exception {
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        VehicleResponse vehicleResponse = vehicleService.getVehicleById(bookingRequest.getVehicleId());
        if (vehicleResponse == null) {
            throw new Exception("Vehicle not found!");
        }

        Booking booking = new Booking();
        booking.setContent(bookingRequest.getContent());
        booking.setTime(bookingRequest.getTime());
        booking.setCustomerId(user.transferUser());
        booking.setVehicleId(vehicleResponse.transferVehicle());

        bookingRepository.addBooking(booking);
    }

    @Override
    public List<BookingResponse> getBookings(int customerId) {
        return bookingRepository.getBookings(customerId);
    }

    @Override
    public List<BookingResponse> getBookings() {
        return bookingRepository.getBookings();
    }

    @Override
    public void updateBooking(String username, int bookingId, String status) throws Exception {
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        BookingResponse booking = bookingRepository.getBookingById(bookingId);
        if (booking == null) {
            throw new Exception("Booking not found!");
        }

        BookingResponse updatedBooking = bookingRepository.updateBooking(user.transferUser(), bookingId, status);

        if (updatedBooking != null && updatedBooking.getStaff() != null) {
            String recipientEmail = updatedBooking.getCustomer().getEmail();
            String message = "Your booking at " + booking.getTime().toString() + " has been confirmed, please come on time!";
            
            emailService.sendMail(recipientEmail, message);
        }
    }

    @Override
    public BookingResponse getBookingById(int id, int customerId) {
        return bookingRepository.getBookingById(id, customerId);
    }

    @Override
    public BookingResponse getBookingById(int id) {
        return bookingRepository.getBookingById(id);
    }

}
