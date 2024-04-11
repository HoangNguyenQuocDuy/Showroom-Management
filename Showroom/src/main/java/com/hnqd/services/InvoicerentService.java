/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.InvoicerentRequest;
import com.hnqd.dto.InvoicerentResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface InvoicerentService {

    void addInvoicerent(InvoicerentRequest invoicerentRequest) throws Exception;

    List<InvoicerentResponse> getInvoicerent(int userId, Map<String, String> params) throws Exception;

    InvoicerentResponse getInvoicerentById(int id);

}
