/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.InvoicerentResponse;
import com.hnqd.dto.RentalResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Invoicerent;
import com.hnqd.pojo.Rental;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.InvoicerentRepository;
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
public class InvoicerentRepositoryImpl implements InvoicerentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addInvoicerent(Invoicerent invoicerent) {
        invoicerent.setCreatedAt(new Date());
        invoicerent.setStatus("Paid");
        Session s = factory.getObject().getCurrentSession();

        s.save(invoicerent);
    }

    @Override
    public List<InvoicerentResponse> getInvoicerents(int userId, String roleName, Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        String customerName = params.get("customerName");
        String specificDate = params.get("specificDate");
        String fromDate = params.get("fromDate");
        String toDate = params.get("toDate");

        String hql = "FROM Invoicerent inv WHERE 1=1";

        try {
            if (roleName.equals("CUSTOMER")) {
                hql += " AND inv.rentalId.customerId.id = :customerId";
            } else if (roleName.equals("ADMIN")) {
                if (customerName != null && !customerName.isEmpty()) {
                    hql += " AND inv.rentalId.customerId.username = :customerName";
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

            Query<Invoicerent> query = session.createQuery(hql, Invoicerent.class);

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

            List<Invoicerent> invoices = query.getResultList();

            return invoices.stream().map(this::mapToInvoicerentResponse).collect(Collectors.toList());
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public InvoicerentResponse getInvoicerentById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Invoicerent WHERE id=:id")
                .setParameter("id", id);

        return mapToInvoicerentResponse((Invoicerent) q.getSingleResult());
    }

    private InvoicerentResponse mapToInvoicerentResponse(Invoicerent invoicerent) {
        InvoicerentResponse invoicerentRes = new InvoicerentResponse();
        invoicerentRes.setId(invoicerent.getId());
        invoicerentRes.setCreatedAt(invoicerent.getCreatedAt());
        invoicerentRes.setAmount(invoicerent.getAmount());
        invoicerentRes.setStatus(invoicerent.getStatus());
        invoicerentRes.setRental(mapToRentalResponse(invoicerent.getRentalId()));

        if (invoicerent.getStatus() != null) {
        }

        if (invoicerent.getUpdatedAt() != null) {
            invoicerentRes.setUpdatedAt(invoicerent.getUpdatedAt());
        }
        return invoicerentRes;
    }

    private RentalResponse mapToRentalResponse(Rental rental) {
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setId(rental.getId());
        rentalResponse.setCreatedDate(rental.getCreatedAt());
        rentalResponse.setTime(rental.getTime());
        rentalResponse.setCustomer(mapToUserResponse(rental.getCustomerId()));
        rentalResponse.setVehicle(mapToVehicleResponse(rental.getVehicleId()));
        if (rental.getStatus() != null) {
            rentalResponse.setStatus(rental.getStatus());
        }

        if (rental.getUpdatedAt() != null) {
            rentalResponse.setUpdatedDate(rental.getUpdatedAt());
        }

        if (rental.getStaffId() != null) {
            rentalResponse.setStaff(mapToUserResponse(rental.getStaffId()));
        }

        return rentalResponse;
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
