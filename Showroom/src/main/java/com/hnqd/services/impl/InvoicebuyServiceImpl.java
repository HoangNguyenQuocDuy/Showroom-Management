/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.dto.InvoicebuyRequest;
import com.hnqd.dto.InvoicebuyResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Invoicebuy;
import com.hnqd.repositories.InvoicebuyRepository;
import com.hnqd.services.InvoicebuyService;
import com.hnqd.services.UserService;
import com.hnqd.services.VehicleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class InvoicebuyServiceImpl implements InvoicebuyService {

    @Autowired
    private InvoicebuyRepository invoicebuyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void addInvoicebuy(InvoicebuyRequest invoicemainbuyRequest) throws Exception {
        UserResponse staff = userService.getUserById(invoicemainbuyRequest.getStaffId());
        if (staff == null) {
            throw new Exception("Staff not found!");
        }
        
        UserResponse customer = userService.getUserById(invoicemainbuyRequest.getCustomerId());
        if (customer == null) {
            throw new Exception("Customer not found!");
        }

        VehicleResponse vehicleResponse = vehicleService.getVehicleById(invoicemainbuyRequest.getVehicleId());
        if (vehicleResponse == null) {
            throw new Exception("Vehicle not found!");
        }

        Invoicebuy invoicebuy = new Invoicebuy();
        invoicebuy.setAmount(invoicemainbuyRequest.getAmount());
        invoicebuy.setVehicleId(vehicleResponse.transferVehicle());
        
        invoicebuy.setStaffId(staff.transferUser());
        invoicebuy.setCustomerId(customer.transferUser());

        invoicebuyRepository.addInvoicebuy(invoicebuy);
    }

    @Override
    public List<InvoicebuyResponse> getInvoicebuys(int userId, Map<String, String> params) throws Exception {
        UserResponse user = userService.getUserById(userId);
        if (user == null) {
            throw new Exception("User not found!");
        }

        return invoicebuyRepository.getInvoicebuys(userId, user.getRoleName(), params);
    }

    @Override
    public InvoicebuyResponse getInvoicebuyById(int id) {
        return invoicebuyRepository.getInvoicebuyById(id);
    }

}
