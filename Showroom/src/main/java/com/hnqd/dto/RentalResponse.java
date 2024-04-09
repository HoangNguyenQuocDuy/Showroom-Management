/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Rental;
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
public class RentalResponse {
    private int id;
    private Date time;
    private String status;
    private VehicleResponse vehicle;
    private Date createdDate;
    private Date updatedDate;
    private UserResponse customer;
    private UserResponse staff;
    
    public Rental transferToBooking() {
        Rental rental = new Rental();
        rental.setId(id);
        rental.setTime(time);
        rental.setStatus(status);
        rental.setCreatedAt(createdDate);
        rental.setUpdatedAt(updatedDate);        
        rental.setVehicleId(vehicle.transferVehicle());        
        rental.setCustomerId(customer.transferUser());        
        rental.setStaffId(staff.transferUser());
        
        return rental;
    }
}
