/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.InvoicemaintenanceResponse;
import com.hnqd.dto.MaintenanceResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.pojo.Invoicemaintenance;
import com.hnqd.pojo.Maintenance;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.repositories.InvoicemaintenanceRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class InvoicemaintenanceRepositoryImpl implements InvoicemaintenanceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addInvoicemaintenance(Invoicemaintenance invoicemaintenance) {
        invoicemaintenance.setCreatedAt(new Date());
        invoicemaintenance.setStatus("Paid");
        Session s = factory.getObject().getCurrentSession();

        s.save(invoicemaintenance);
    }

    @Override
    public List<InvoicemaintenanceResponse> getInvoicemaintenances(int userId, String roleName, Map<String, String> params) {

        Session session = factory.getObject().getCurrentSession();
        String customerName = params.get("customerName");
        String specificDate = params.get("specificDate");
        String fromDate = params.get("fromDate");
        String toDate = params.get("toDate");

        String hql = "FROM Invoicemaintenance inv WHERE 1=1";

        try {
            if (roleName.equals("CUSTOMER")) {
                hql += " AND inv.maintenanceId.customerId.id = :customerId";
            } else if (roleName.equals("ADMIN")) {
                if (customerName != null && !customerName.isEmpty()) {
                    hql += " AND inv.maintenanceId.customerId.username = :customerName";
                }
            } else {
                return null;
            }

            if (specificDate != null && !specificDate.isEmpty()) {
                hql += " AND DATE(inv.createdAt) = :specificDate";
            }
            if (fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()) {
                hql += " AND inv.createdAt BETWEEN :fromDate AND :toDate";
            }

            Query<Invoicemaintenance> query = session.createQuery(hql, Invoicemaintenance.class);

            if (roleName.equals("CUSTOMER")) {
                query.setParameter("customerId", userId);
            }
            if (customerName != null && !customerName.isEmpty()) {
                query.setParameter("customerName", customerName);
            }
            if (specificDate != null && !specificDate.isEmpty()) {
                Date specificDateTF = new SimpleDateFormat("yyyy-MM-dd").parse(specificDate);

                query.setParameter("specificDate", specificDateTF);
            }
            if (fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()) {
                Date fromDateTF = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
                Date toDateTF = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);

                query.setParameter("fromDate", fromDate);
                query.setParameter("toDate", toDate);
            }

            List<Invoicemaintenance> invoices = query.getResultList();

            return invoices.stream().map(this::mapToInvoicemaintenanceResponse).collect(Collectors.toList());
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public InvoicemaintenanceResponse getInvoicemaintenanceById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Invoicemaintenance WHERE id=:id")
                .setParameter("id", id);

        return mapToInvoicemaintenanceResponse((Invoicemaintenance) q.getSingleResult());
    }

    private InvoicemaintenanceResponse mapToInvoicemaintenanceResponse(Invoicemaintenance invoicemaintenance) {
        InvoicemaintenanceResponse invoicemaintenanceResponse = new InvoicemaintenanceResponse();
        invoicemaintenanceResponse.setId(invoicemaintenance.getId());
        invoicemaintenanceResponse.setCreatedAt(invoicemaintenance.getCreatedAt());
        invoicemaintenanceResponse.setAmount(invoicemaintenance.getAmount());
        invoicemaintenanceResponse.setStatus(invoicemaintenance.getStatus());
        invoicemaintenanceResponse.setMaintenance(mapToMaintenanceResponse(invoicemaintenance.getMaintenanceId()));

        if (invoicemaintenance.getUpdatedAt() != null) {
            invoicemaintenanceResponse.setUpdatedAt(invoicemaintenance.getUpdatedAt());
        }
        
        if (invoicemaintenance.getStaffId() != null) {
            invoicemaintenanceResponse.setStaff(mapToUserResponse(invoicemaintenance.getStaffId()));
        }
        return invoicemaintenanceResponse;
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
}
