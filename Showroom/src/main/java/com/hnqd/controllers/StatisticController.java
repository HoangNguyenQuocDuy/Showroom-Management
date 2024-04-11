/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dao.InvoiceDao;
import com.hnqd.dto.StatistisResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/statistics")
public class StatisticController {

    @Autowired
    private InvoiceDao invoiceDao;

    @GetMapping("/")
    public ResponseEntity<?> getMonthlyStatsByYear(
            @RequestParam("year") int year
    ) {
        List<StatistisResponse> monthlyStatsList = invoiceDao.getMonthlyStatsByYear(year);
        try {
             return ResponseEntity.status(HttpStatus.OK).body(
                    monthlyStatsList
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get statistic: " + e.getMessage()
            );
        }
    }
}
