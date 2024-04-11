/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Invoicemaintenance;
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
@NoArgsConstructor
@AllArgsConstructor
public class InvoicemaintenanceResponse {

    private int id;
    private String status;
    private BigDecimal amount;
    private Date createdAt;
    private Date updatedAt;
    private MaintenanceResponse maintenance;
    private UserResponse staff;

    public Invoicemaintenance transferToMaintenance() throws ParseException {
        Invoicemaintenance invoicemaintenance = new Invoicemaintenance();
        invoicemaintenance.setId(id);
        invoicemaintenance.setStatus(status);
        invoicemaintenance.setAmount(amount);
        invoicemaintenance.setCreatedAt(createdAt);
        if (updatedAt != null) {
            invoicemaintenance.setUpdatedAt(updatedAt);
        }
        if (staff != null) {
            invoicemaintenance.setStaffId(staff.transferUser());
        }
        invoicemaintenance.setMaintenanceId(maintenance.transferToMaintenance());

        return invoicemaintenance;
    }
}
