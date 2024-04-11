/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.MaintenanceRequest;
import com.hnqd.dto.MaintenanceResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface MaintenanceService {
    void addMaintenance(MaintenanceRequest maintenance, String token) throws Exception;

    List<MaintenanceResponse> getMaintenances(String token);
    
    MaintenanceResponse getMaintenanceById(int id);

    void updateMaintenance(String username, int maintenanceId, String status) throws Exception;
}
