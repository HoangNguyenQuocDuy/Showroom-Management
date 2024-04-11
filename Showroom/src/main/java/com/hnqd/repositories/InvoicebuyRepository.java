/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.InvoicebuyResponse;
import com.hnqd.pojo.Invoicebuy;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicebuyRepository {
    void addInvoicebuy(Invoicebuy invoicemaintenance);

    List<InvoicebuyResponse> getInvoicebuys(int userId, String roleName, Map<String, String> params);

    InvoicebuyResponse getInvoicebuyById(int id);
}
