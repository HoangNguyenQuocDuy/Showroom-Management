/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.InvoicerentRequest;
import com.hnqd.services.InvoicerentService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/invoicerents")
public class InvoicerentController {

    @Autowired
    private InvoicerentService invoicerentService;

    @PostMapping("/create")
    public ResponseEntity<?> createInvoicerent(@RequestBody InvoicerentRequest invoicerentRequest) {

        try {
            invoicerentService.addInvoicerent(invoicerentRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create invoicerent successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create invoicerent: " + e.toString()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getInvoicerents(
            @RequestParam Map<String, String> params) {

        try {
            int userId = Integer.parseInt(params.get("userId"));
            
            return ResponseEntity.status(HttpStatus.OK).body(
                    invoicerentService.getInvoicerent(userId, params)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get invoicerents " + e.getMessage()
            );
        }
    }

    @GetMapping("/{invoicerentId}")
    public ResponseEntity<?> getinvoicerents(
            @PathVariable("invoicerentId") int invoicerentId) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    invoicerentService.getInvoicerentById(invoicerentId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get invoicerents " + e.getMessage()
            );
        }
    }
}
