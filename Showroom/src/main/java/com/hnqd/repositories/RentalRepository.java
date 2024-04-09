/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.RentalResponse;
import com.hnqd.pojo.Rental;
import com.hnqd.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface RentalRepository {

    void addRental(Rental maintenance);

    List<RentalResponse> getRental(int userId, String roleName);

    RentalResponse updateRental(User staff, int maintenanceId, String status);

    RentalResponse getRentalById(int id);

}
