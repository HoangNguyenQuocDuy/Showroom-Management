/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.dto;

import java.sql.Timestamp;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp time;
    private int vehicleId;
}
