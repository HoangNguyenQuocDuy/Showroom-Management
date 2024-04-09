/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Maintenance;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse {
    private int id;
    private String content;
    private String status;
    private String createdDate;
    private String updatedDate;
    private String time;
    private UserResponse customer;
    private UserResponse staff;
    private ShowroomResponse showroom;
    
    public Maintenance transferToBooking() throws ParseException {
        
        Maintenance maintenance = new Maintenance();
        maintenance.setId(id);
        maintenance.setContent(content);
        maintenance.setTime(convertToDateTime(time));
        maintenance.setStatus(status);
        maintenance.setCreatedDate(convertToDateTime(createdDate));
        maintenance.setUpdatedDate(convertToDateTime(updatedDate));        
        maintenance.setShowroomId(showroom.transferToShowroom());        
        maintenance.setCustomerId(customer.transferUser());        
        maintenance.setStaffId(staff.transferUser());
        
        return maintenance;
    }
    
    public Date convertToDateTime(String dateTimeString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        
        Date dateTime = dateFormat.parse(dateTimeString);
        return dateTime;
    }

}
