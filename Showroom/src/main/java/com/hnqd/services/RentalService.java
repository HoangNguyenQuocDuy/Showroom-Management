/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.RentalRequest;
import com.hnqd.dto.RentalResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface RentalService {
    void addRental(RentalRequest rentalRequest, String username) throws Exception;

    List<RentalResponse> getRentals(String token);

    void updateRentals(String username, int rentalId, String status) throws Exception;
}
