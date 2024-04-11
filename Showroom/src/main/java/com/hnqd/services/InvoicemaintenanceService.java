/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.InvoicemaintenanceRequest;
import com.hnqd.dto.InvoicemaintenanceResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicemaintenanceService {
    void addInvoicemaintenance(InvoicemaintenanceRequest invoicemaintenanceRequest) throws Exception;

    List<InvoicemaintenanceResponse> getInvoicemaintenances(int userId, Map<String, String> params) throws Exception;

    InvoicemaintenanceResponse getInvoicemaintenanceById(int id);
}
