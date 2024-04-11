/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Invoicebuy;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoicebuyResponse {
    private int id;
    private String status;
    private BigDecimal amount;
    private Date createdAt;
    private Date updatedAt;
    private VehicleResponse vehicle;
    private UserResponse customer;
    private UserResponse staff;
    
    public Invoicebuy transferToInvoicebuy() throws ParseException {
        Invoicebuy invoicebuy = new Invoicebuy();
        invoicebuy.setId(id);
        invoicebuy.setStatus(status);
        invoicebuy.setAmount(amount);
        invoicebuy.setCreatedAt(createdAt);
        invoicebuy.setVehicleId(vehicle.transferVehicle());
        invoicebuy.setCustomerId(customer.transferUser());
        if (updatedAt != null) {
            invoicebuy.setUpdatedAt(updatedAt);
        }
        if (staff != null) {
            invoicebuy.setStaffId(staff.transferUser());
        }

        return invoicebuy;
    }
}
