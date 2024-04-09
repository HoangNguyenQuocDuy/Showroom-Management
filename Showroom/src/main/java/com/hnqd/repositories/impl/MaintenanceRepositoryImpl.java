/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.MaintenanceResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.repositories.MaintenanceRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class MaintenanceRepositoryImpl implements MaintenanceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addMaintenance(Maintenance maintenance) {
        maintenance.setCreatedDate(new Date());
        maintenance.setStatus("Pending");
        Session s = factory.getObject().getCurrentSession();

        s.save(maintenance);
    }

    @Override
    public List<MaintenanceResponse> getMaintenances(int userId, String roleName) {
        Session s = factory.getObject().getCurrentSession();

        if (roleName.equals("CUSTOMER")) {
            Query<Maintenance> query = s.createQuery(
                    "FROM Maintenance WHERE customerId.id = :customerId", Maintenance.class);
            query.setParameter("customerId", userId);
            List<Maintenance> maintenances = query.getResultList();

            List<MaintenanceResponse> maintenanceResponses = new ArrayList<>();
            for (Maintenance maintenance : maintenances) {
                maintenanceResponses.add(mapToMaintenanceResponse(maintenance));
            }

            return maintenanceResponses;
        } else if (roleName.equals("STAFF")) {
            Query<Maintenance> query = s.createQuery(
                    "FROM Maintenance WHERE status =:status AND staffId IS NULL ORDER BY createdDate ASC",
                    Maintenance.class);
            query.setParameter("status", "Pending");
            List<Maintenance> maintenances = query.getResultList();

            List<MaintenanceResponse> maintenanceRes = new ArrayList<>();
            for (Maintenance maintenance : maintenances) {
                MaintenanceResponse maintenanceResponse = mapToMaintenanceResponse(maintenance);
                maintenanceRes.add(maintenanceResponse);
            }

            return maintenanceRes;
        }

        return null;
    }

    @Override
    public MaintenanceResponse updateMaintenance(User staff, int maintenanceId, String status) {
        Session s = factory.getObject().getCurrentSession();
        Maintenance maintenance = s.get(Maintenance.class, maintenanceId);

        if (maintenance != null) {
            maintenance.setStatus(status);
            maintenance.setUpdatedDate(new Date());
            maintenance.setStaffId(staff);

            s.update(maintenance);

            return mapToMaintenanceResponse(maintenance);
        }

        return null;
    }

    private MaintenanceResponse mapToMaintenanceResponse(Maintenance maintenance) {
        MaintenanceResponse maintenanceRes = new MaintenanceResponse();
        maintenanceRes.setId(maintenance.getId());
        maintenanceRes.setContent(maintenance.getContent());
        maintenanceRes.setCreatedDate(maintenance.getCreatedDate().toString());
        maintenanceRes.setTime(maintenance.getTime().toString());
        maintenanceRes.setCustomer(mapToUserResponse(maintenance.getCustomerId()));
        maintenanceRes.setShowroom(mapShowroomResponse(maintenance.getShowroomId()));
        if (maintenance.getStatus() != null) {
            maintenanceRes.setStatus(maintenance.getStatus());
        }

        if (maintenance.getUpdatedDate() != null) {
            maintenanceRes.setUpdatedDate(maintenance.getUpdatedDate().toString());
        }

        if (maintenance.getStaffId() != null) {
            maintenanceRes.setStaff(mapToUserResponse(maintenance.getStaffId()));
        }

        return maintenanceRes;
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userRes = new UserResponse();
        userRes.setUserId(user.getId());
        userRes.setImage(user.getImage());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        userRes.setPassword(user.getPassword());
        userRes.setRoleName(user.getRoleName());

        return userRes;
    }

    private ShowroomResponse mapShowroomResponse(Showroom showroom) {
        ShowroomResponse showroomRes = new ShowroomResponse();
        showroomRes.setId(showroom.getId());
        showroomRes.setName(showroom.getName());
        showroomRes.setLocation(showroom.getLocation());

        return showroomRes;
    }

    @Override
    public MaintenanceResponse getBookingById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Maintenance WHERE id=:id").setParameter("id", id);

        return mapToMaintenanceResponse((Maintenance) q.getSingleResult());
    }

}
