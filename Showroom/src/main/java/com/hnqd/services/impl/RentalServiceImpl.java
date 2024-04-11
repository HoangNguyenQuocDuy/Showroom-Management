/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.dto.RentalRequest;
import com.hnqd.dto.RentalResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Rental;
import com.hnqd.repositories.RentalRepository;
import com.hnqd.services.EmailService;
import com.hnqd.services.RentalService;
import com.hnqd.services.UserService;
import com.hnqd.services.VehicleService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class RentalServiceImpl implements RentalService{
    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void addRental(RentalRequest rentalRequest, String token) throws Exception {
        String username = jwtTokenUtil.extractUsername(token);
        
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        VehicleResponse vehicleResponse = vehicleService.getVehicleById(rentalRequest.getVehicleId());
        if (vehicleResponse == null) {
            throw new Exception("Vehicle not found!");
        }

        Rental rental = new Rental();
        rental.setTime(convertToDateTime(rentalRequest.getTime().toString()));
        rental.setCustomerId(user.transferUser());
        rental.setVehicleId(vehicleResponse.transferVehicle());

        rentalRepository.addRental(rental);
    }
    
    public Date convertToDateTime(String dateTimeString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date dateTime = dateFormat.parse(dateTimeString);
        return dateTime;
    }

    @Override
    public List<RentalResponse> getRentals(String token) {
        Map<String, Object> userData = jwtTokenUtil.extractUserData(token);

        return rentalRepository.getRental((int) userData.get("userId"),
                (String) userData.get("roleName"));
    }

    @Override
    public void updateRentals(String token, int rentalId, String status) throws Exception {
        String username = jwtTokenUtil.extractUsername(token);
        
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        RentalResponse rentalResponse = rentalRepository.getRentalById(rentalId);
        if (rentalResponse == null) {
            throw new Exception("Maintenance not found!");
        }

        RentalResponse updatedRental = rentalRepository.updateRental(user.transferUser(), rentalId, status);

        if (updatedRental != null && updatedRental.getStaff() != null) {
            String recipientEmail = updatedRental.getCustomer().getEmail();
            String message = "Your Rental of vehicle \'" + updatedRental.getVehicle().getName() + "\' has been confirmed";

            emailService.sendMail(recipientEmail, message);
        }
    }

    @Override
    public RentalResponse getRentalById(int id) {
        return rentalRepository.getRentalById(id);
    }
    
}
