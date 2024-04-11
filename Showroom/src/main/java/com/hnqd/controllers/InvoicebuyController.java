/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.InvoicebuyRequest;
import com.hnqd.services.InvoicebuyService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/invoicebuys")
public class InvoicebuyController {

    @Autowired
    private InvoicebuyService invoicebuyService;

    @PostMapping("/create")
    public ResponseEntity<?> createInvoicemaintenance(
            @RequestBody InvoicebuyRequest invoicemaintenanceRequest
    ) {

        try {
            invoicebuyService.addInvoicebuy(invoicemaintenanceRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create invoicebuy successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create invoicebuy: " + e.toString()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getInvoicemaintenances(
            @RequestParam Map<String, String> params) {
        
        int userId = Integer.parseInt(params.get("userId"));

        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    invoicebuyService.getInvoicebuys(userId, params)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get invoicebuy" + e.getMessage()
            );
        }
    }

    @GetMapping("/{invoicerentId}")
    public ResponseEntity<?> getInvoicemaintenance(
            @PathVariable("invoicebuyId") int invoicebuyId) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    invoicebuyService.getInvoicebuyById(invoicebuyId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get invoicebuy " + e.getMessage()
            );
        }
    }
}
