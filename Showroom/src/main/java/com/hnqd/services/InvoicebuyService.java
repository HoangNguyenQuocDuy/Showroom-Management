/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.InvoicebuyRequest;
import com.hnqd.dto.InvoicebuyResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicebuyService {
    void addInvoicebuy(InvoicebuyRequest invoicemainbuyRequest) throws Exception;

    List<InvoicebuyResponse> getInvoicebuys(int userId, Map<String, String> params) throws Exception;

    InvoicebuyResponse getInvoicebuyById(int id);
}
