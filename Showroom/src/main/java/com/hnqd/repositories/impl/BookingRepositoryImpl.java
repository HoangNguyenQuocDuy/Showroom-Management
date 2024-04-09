/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories.impl;

import com.hnqd.dto.BookingResponse;
import com.hnqd.dto.ShowroomResponse;
import com.hnqd.dto.UserResponse;
import com.hnqd.dto.VehicleResponse;
import com.hnqd.pojo.Booking;
import com.hnqd.pojo.Showroom;
import com.hnqd.pojo.User;
import com.hnqd.pojo.Vehicle;
import com.hnqd.repositories.BookingRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addBooking(Booking booking) {
        booking.setCreatedDate(new Date());
        booking.setStatus("Pending");
        Session s = factory.getObject().getCurrentSession();

        s.save(booking);
    }

    @Override
    public List<BookingResponse> getBookings(int customerId) {
        Session s = factory.getObject().getCurrentSession();
        Query<Booking> query = s.createQuery(
                "FROM Booking WHERE customerId.id = :customerId", Booking.class);
        query.setParameter("customerId", customerId);
        List<Booking> bookings = query.getResultList();

        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponses.add(mapToBookingResponse(booking));
        }

        return bookingResponses;
    }

    @Override
    public List<BookingResponse> getBookings() {
        Session s = factory.getObject().getCurrentSession();
        Query<Booking> query = s.createQuery(
                "FROM Booking WHERE status =:status AND staffId IS NULL ORDER BY createdDate ASC",
                Booking.class);
        query.setParameter("status", "Pending");
        List<Booking> bookings = query.getResultList();

        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponse bookingResponse = mapToBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }

        return bookingResponses;
    }

    @Override
    public BookingResponse updateBooking(User staff, int bookingId, String status) {
        Session s = factory.getObject().getCurrentSession();
        Booking booking = s.get(Booking.class, bookingId);

        if (booking != null) {
            booking.setStatus(status);
            booking.setUpdatedDate(new Date());
            booking.setStaffId(staff);
            
            s.update(booking);

            return mapToBookingResponse(booking);
        }

        return null;
    }

    @Override
    public BookingResponse getBookingById(int id, int customerId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Booking WHERE id=:id AND customerId=:customerId")
                .setParameter("id", id)
                .setParameter("customerId", customerId);

        return mapToBookingResponse((Booking) q.getSingleResult());
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        BookingResponse bookingRes = new BookingResponse();
        bookingRes.setId(booking.getId());
        bookingRes.setContent(booking.getContent());
        bookingRes.setCreatedDate(booking.getCreatedDate());
        bookingRes.setTime((Timestamp.valueOf(booking.getTime().toString())) );
        bookingRes.setVehicle(mapToVehicleResponse(booking.getVehicleId()));
        bookingRes.setCustomer(mapToUserResponse(booking.getCustomerId()));
        if (booking.getStatus() != null) {
            bookingRes.setStatus(booking.getStatus());
        }

        if (booking.getUpdatedDate() != null) {
            bookingRes.setUpdatedDate(booking.getUpdatedDate());
        }

        if (booking.getStaffId() != null) {
            bookingRes.setStaff(mapToUserResponse(booking.getStaffId()));
        }

        return bookingRes;
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
    public BookingResponse getBookingById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Booking WHERE id=:id")
                .setParameter("id", id);

        return mapToBookingResponse((Booking) q.getSingleResult());
    }
}
