/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.InvoicemaintenanceResponse;
import com.hnqd.pojo.Invoicemaintenance;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicemaintenanceRepository {
    void addInvoicemaintenance(Invoicemaintenance invoicemaintenance);

    List<InvoicemaintenanceResponse> getInvoicemaintenances(int userId, String roleName, Map<String, String> params);

    InvoicemaintenanceResponse getInvoicemaintenanceById(int id);
}
