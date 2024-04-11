/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.InvoicerentResponse;
import com.hnqd.pojo.Invoicerent;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicerentRepository {
     void addInvoicerent(Invoicerent invoicerent);

    List<InvoicerentResponse> getInvoicerents(int userId, String roleName, Map<String, String> params);

//    InvoicerentResponse updateInvoicerent(User staff, int maintenanceId, String status);

//    MaintenanceResponse getBookingById(int id, int customerId);

    InvoicerentResponse getInvoicerentById(int id);
}
