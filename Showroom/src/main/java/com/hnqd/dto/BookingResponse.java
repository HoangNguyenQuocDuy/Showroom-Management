/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Booking;
import java.sql.Timestamp;
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
public class BookingResponse {
    private int id;
    private String content;
    private Timestamp time;
    private String status;
    private VehicleResponse vehicle;
    private Date createdDate;
    private Date updatedDate;
    private UserResponse customer;
    private UserResponse staff;
    
    public Booking transferToBooking() {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setContent(content);
        booking.setTime(time);
        booking.setStatus(status);
        booking.setCreatedDate(createdDate);
        booking.setUpdatedDate(updatedDate);        
        booking.setVehicleId(vehicle.transferVehicle());        
        booking.setCustomerId(customer.transferUser());        
        booking.setStaffId(staff.transferUser());
        
        return booking;
    }
}
