/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.InvoicebuyResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Invoicebuy;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.InvoicebuyRepository;
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
public class InvoicebuyRepositoryImpl implements InvoicebuyRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addInvoicebuy(Invoicebuy invoicebuy) {
        invoicebuy.setCreatedAt(new Date());
        invoicebuy.setStatus("Paid");
        Session s = factory.getObject().getCurrentSession();

        s.save(invoicebuy);
    }

    @Override
    public List<InvoicebuyResponse> getInvoicebuys(int userId, String roleName, Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        String customerName = params.get("customerName");
        String specificDate = params.get("specificDate");
        String fromDate = params.get("fromDate");
        String toDate = params.get("toDate");

        String hql = "FROM Invoicebuy inv WHERE 1=1";

        try {
            if (roleName.equals("CUSTOMER")) {
                hql += " AND inv.customerId.id = :customerId";
            } else if (roleName.equals("ADMIN")) {
                if (customerName != null && !customerName.isEmpty()) {
                    hql += " AND inv.customerId.username = :customerName";
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

            Query<Invoicebuy> query = session.createQuery(hql, Invoicebuy.class);

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

            List<Invoicebuy> invoices = query.getResultList();

            return invoices.stream().map(this::mapToInvoicebuyResponse).collect(Collectors.toList());
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public InvoicebuyResponse getInvoicebuyById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Invoicebuy WHERE id=:id")
                .setParameter("id", id);

        return mapToInvoicebuyResponse((Invoicebuy) q.getSingleResult());
    }

    
    private InvoicebuyResponse mapToInvoicebuyResponse(Invoicebuy invoicebuy) {
        InvoicebuyResponse invoicebuyResponse = new InvoicebuyResponse();
        invoicebuyResponse.setId(invoicebuy.getId());
        invoicebuyResponse.setCreatedAt(invoicebuy.getCreatedAt());
        invoicebuyResponse.setAmount(invoicebuy.getAmount());
        invoicebuyResponse.setStatus(invoicebuy.getStatus());
        invoicebuyResponse.setVehicle(mapToVehicleResponse(invoicebuy.getVehicleId()));

        if (invoicebuy.getUpdatedAt() != null) {
            invoicebuyResponse.setUpdatedAt(invoicebuyResponse.getUpdatedAt());
        }
        
        if (invoicebuy.getStaffId() != null) {
            invoicebuyResponse.setStaff(mapToUserResponse(invoicebuy.getCustomerId()));
        }
        
        if (invoicebuy.getCustomerId() != null) {
            invoicebuyResponse.setCustomer(mapToUserResponse(invoicebuy.getCustomerId()));
        }
        return invoicebuyResponse;
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
    
    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        VehicleResponse vehicleRes = new VehicleResponse();
        vehicleRes.setId(vehicle.getId());
        vehicleRes.setName(vehicle.getName());
        vehicleRes.setPrice(vehicle.getPrice());
        vehicleRes.setSpecifications(vehicle.getSpecifications());
        vehicleRes.setDescription(vehicle.getDescription());
        vehicleRes.setBrand(vehicle.getBrand());
        vehicleRes.setStatus(vehicle.getStatus());
        vehicleRes.setShowroom(mapShowroomResponse(vehicle.getShowroomId()));

        return vehicleRes;
    }
}
