/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Vehicle;
import java.math.BigDecimal;
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
public class VehicleResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private String specifications;
    private String description;
    private String status;
    private String brand;
    private ShowroomResponse showroom;
    private String image;
    
    public Vehicle transferVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setName(name);
        vehicle.setPrice(price);
        vehicle.setSpecifications(specifications);
        vehicle.setDescription(description);
        vehicle.setStatus(status);      
        vehicle.setShowroomId(showroom.transferToShowroom());
        vehicle.setBrand(brand);
        
        return vehicle;
    }
}
