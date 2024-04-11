/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import com.hnqd.pojo.Invoicerent;
import java.math.BigDecimal;
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
public class InvoicerentResponse {

    private int id;
    private String status;
    private BigDecimal amount;
    private Date createdAt;
    private Date updatedAt;
    private RentalResponse rental;
    private UserResponse staff;

    public Invoicerent transferToInvoicerent() {
        Invoicerent invoicerent = new Invoicerent();
        invoicerent.setId(id);
        invoicerent.setStatus(status);
        invoicerent.setAmount(amount);
        if (updatedAt != null) {
            invoicerent.setUpdatedAt(updatedAt);
        }
        if (staff != null) {
            invoicerent.setStaffId(staff.transferUser());
        }
        invoicerent.setCreatedAt(createdAt);
        invoicerent.setRentalId(rental.tranferToRental());

        return invoicerent;
    }
}
