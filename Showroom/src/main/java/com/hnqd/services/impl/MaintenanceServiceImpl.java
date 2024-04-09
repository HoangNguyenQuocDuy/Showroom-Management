/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.dto.MaintenanceRequest;
import com.hnqd.dto.MaintenanceResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.Maintenance;
import com.hnqd.repositories.MaintenanceRepository;
import com.hnqd.services.EmailService;
import com.hnqd.services.ShowroomService;
import com.hnqd.services.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hnqd.services.MaintenanceService;

/**
 *
 * @author DELL
 */
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ShowroomService showroomService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void addMaintenance(MaintenanceRequest maintenanceRequest, String token) throws Exception {
        String username = jwtTokenUtil.extractUsername(token);
        
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        ShowroomResponse showroomResponse = showroomService.getShowroomById(maintenanceRequest.getShowroomId());
        if (showroomResponse == null) {
            throw new Exception("Showroom not found!");
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setContent(maintenanceRequest.getContent());
        maintenance.setTime(convertToDateTime(maintenanceRequest.getTime()));
        maintenance.setCustomerId(user.transferUser());
        maintenance.setShowroomId(showroomResponse.transferToShowroom());

        maintenanceRepository.addMaintenance(maintenance);
    }

    public Date convertToDateTime(String dateTimeString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date dateTime = dateFormat.parse(dateTimeString);
        return dateTime;
    }

    @Override
    public List<MaintenanceResponse> getMaintenances(String token) {
        Map<String, Object> userData = jwtTokenUtil.extractUserData(token);

        return maintenanceRepository.getMaintenances((int) userData.get("userId"),
                (String) userData.get("roleName"));
    }

    @Override
    public void updateMaintenance(String token, int maintenanceId, String status) throws Exception {
        String username = jwtTokenUtil.extractUsername(token);
        
        UserResponse user = userService.getUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found!");
        }

        MaintenanceResponse maintenance = maintenanceRepository.getBookingById(maintenanceId);
        if (maintenance == null) {
            throw new Exception("Maintenance not found!");
        }

        MaintenanceResponse updatedMaintenance = maintenanceRepository.updateMaintenance(user.transferUser(), maintenanceId, status);

        if (updatedMaintenance != null && updatedMaintenance.getStaff() != null) {
            String recipientEmail = updatedMaintenance.getCustomer().getEmail();
            String message = "Your booking maintenance at " + updatedMaintenance.getTime() + " has been confirmed, please come on time!";

            emailService.sendMail(recipientEmail, message);
        }
    }

}
