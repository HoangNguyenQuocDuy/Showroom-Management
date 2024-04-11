/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.configs.JwtTokenUtil;
import com.hnqd.dto.InvoicerentRequest;
import com.hnqd.dto.InvoicerentResponse;
import com.hnqd.dto.RentalResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.Invoicerent;
import com.hnqd.repositories.InvoicerentRepository;
import com.hnqd.services.InvoicerentService;
import com.hnqd.services.RentalService;
import com.hnqd.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class InvoicerentServiceImpl implements InvoicerentService {

    @Autowired
    private InvoicerentRepository invoicerentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RentalService rentalService;

    @Override
    public void addInvoicerent(InvoicerentRequest invoicerentRequest) throws Exception {

        UserResponse user = userService.getUserById(invoicerentRequest.getStaffId());
        if (user == null) {
            throw new Exception("User not found!");
        }

        RentalResponse rentalResponse = rentalService.getRentalById(invoicerentRequest.getRentalId());
        if (rentalResponse == null) {
            throw new Exception("Rental not found!");
        }

        if (rentalResponse.getStaff() == null) {
            throw new Exception("Rental hasn't been confirmed!");
        }

        Invoicerent invoicerent = new Invoicerent();
        invoicerent.setAmount(invoicerentRequest.getAmount());
        invoicerent.setRentalId(rentalResponse.tranferToRental());
        invoicerent.setStaffId(user.transferUser());

        invoicerentRepository.addInvoicerent(invoicerent);
    }

    @Override
    public List<InvoicerentResponse> getInvoicerent(int userId, Map<String, String> params) throws Exception {
        UserResponse user = userService.getUserById(userId);
        if (user == null) {
            throw new Exception("User not found!");
        }

        return invoicerentRepository.getInvoicerents(userId, user.getRoleName(), params);
    }

    @Override
    public InvoicerentResponse getInvoicerentById(int id) {
        return invoicerentRepository.getInvoicerentById(id);
    }

}
