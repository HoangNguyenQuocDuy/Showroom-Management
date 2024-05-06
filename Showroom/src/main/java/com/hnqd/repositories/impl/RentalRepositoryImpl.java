/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.RentalResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Rental;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.RentalRepository;
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
public class RentalRepositoryImpl implements RentalRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addRental(Rental rental) {
        rental.setCreatedAt(new Date());
        rental.setStatus("Pending");
        Session s = factory.getObject().getCurrentSession();

        s.save(rental);
    }

    @Override
    public List<RentalResponse> getRental(int userId, String roleName) {
        Session s = factory.getObject().getCurrentSession();

        if (roleName.equals("CUSTOMER")) {
            Query<Rental> query = s.createQuery(
                    "FROM Rental WHERE customerId.id = :customerId", Rental.class);
            query.setParameter("customerId", userId);
            List<Rental> rentals = query.getResultList();

            List<RentalResponse> rentalResponses = new ArrayList<>();
            for (Rental rental : rentals) {
                rentalResponses.add(mapToRentalResponse(rental));
            }

            return rentalResponses;
        } else if (roleName.equals("STAFF")) {
            Query<Rental> query = s.createQuery(
                    "FROM Rental WHERE status =:status",
                    Rental.class);
            query.setParameter("status", "Pending");
            List<Rental> rentals = query.getResultList();

            List<RentalResponse> rentalResponses = new ArrayList<>();
            for (Rental rental : rentals) {
                rentalResponses.add(mapToRentalResponse(rental));
            }

            return rentalResponses;
        } else if (roleName.equals("ADMIN")) {
            Query<Rental> query = s.createQuery(
                    "FROM Rental",
                    Rental.class);
            List<Rental> rentals = query.getResultList();

            List<RentalResponse> rentalResponses = new ArrayList<>();
            for (Rental rental : rentals) {
                rentalResponses.add(mapToRentalResponse(rental));
            }

            return rentalResponses;
        }

        return null;
    }

    @Override
    public RentalResponse updateRental(User staff, int maintenanceId, String status) {
        Session s = factory.getObject().getCurrentSession();
        Rental rental = s.get(Rental.class, maintenanceId);

        if (rental != null) {
            rental.setStatus(status);
            rental.setUpdatedAt(new Date());
            rental.setStaffId(staff);

            s.update(rental);

            return mapToRentalResponse(rental);
        }

        return null;
    }

    @Override
    public RentalResponse getRentalById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Rental WHERE id=:id").setParameter("id", id);

        return mapToRentalResponse((Rental) q.getSingleResult());
    }

    private RentalResponse mapToRentalResponse(Rental rental) {
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setId(rental.getId());
        rentalResponse.setCreatedDate(rental.getCreatedAt());
        rentalResponse.setStartDate(rental.getStartDate());
        rentalResponse.setEndDate(rental.getEndDate());
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
        vehicleRes.setImage(vehicle.getImage());
        vehicleRes.setSpecifications(vehicle.getSpecifications());
        vehicleRes.setDescription(vehicle.getDescription());
        vehicleRes.setBrand(vehicle.getBrand());
        vehicleRes.setStatus(vehicle.getStatus());
        vehicleRes.setShowroom(mapShowroomResponse(vehicle.getShowroomId()));

        return vehicleRes;
    }

}
