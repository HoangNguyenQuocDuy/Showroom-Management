/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services.impl;

import com.hnqd.dto.InvoicemaintenanceRequest;
import com.hnqd.dto.InvoicemaintenanceResponse;
import com.hnqd.dto.MaintenanceResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.Invoicemaintenance;
import com.hnqd.repositories.InvoicemaintenanceRepository;
import com.hnqd.services.InvoicemaintenanceService;
import com.hnqd.services.MaintenanceService;
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
public class InvoicemaintenanceServiceImpl implements InvoicemaintenanceService {

    @Autowired
    private InvoicemaintenanceRepository invoicemaintenanceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Override
    public void addInvoicemaintenance(InvoicemaintenanceRequest invoicemaintenanceRequest) throws Exception {
        UserResponse user = userService.getUserById(invoicemaintenanceRequest.getStaffId());
        if (user == null) {
            throw new Exception("User not found!");
        }

        MaintenanceResponse maintenanceResponse = maintenanceService.getMaintenanceById(invoicemaintenanceRequest.getMaintenanceId());
        if (maintenanceResponse == null) {
            throw new Exception("Maintenance not found!");
        }

        if (maintenanceResponse.getStaff() == null) {
            throw new Exception("Maintenance hasn't been confirmed!");
        }

        Invoicemaintenance invoicemaintenance = new Invoicemaintenance();
        invoicemaintenance.setAmount(invoicemaintenanceRequest.getAmount());
        invoicemaintenance.setMaintenanceId(maintenanceResponse.transferToMaintenance());
        invoicemaintenance.setStaffId(user.transferUser());

        invoicemaintenanceRepository.addInvoicemaintenance(invoicemaintenance);
    }

    @Override
    public List<InvoicemaintenanceResponse> getInvoicemaintenances(int userId, Map<String, String> params) throws Exception {
        UserResponse user = userService.getUserById(userId);
        if (user == null) {
            throw new Exception("User not found!");
        }

        return invoicemaintenanceRepository.getInvoicemaintenances(userId, user.getRoleName(), params);
    }

    @Override
    public InvoicemaintenanceResponse getInvoicemaintenanceById(int id) {
        return invoicemaintenanceRepository.getInvoicemaintenanceById(id);
    }

}
