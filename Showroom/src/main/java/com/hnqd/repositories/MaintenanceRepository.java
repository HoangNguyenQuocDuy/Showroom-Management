/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.MaintenanceResponse;
import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface MaintenanceRepository {
    void addMaintenance(Maintenance maintenance);

    List<MaintenanceResponse> getMaintenances(int userId, String roleName);

    MaintenanceResponse updateMaintenance(User staff, int maintenanceId, String status);

    MaintenanceResponse getMaintenanceById(int id);
}
