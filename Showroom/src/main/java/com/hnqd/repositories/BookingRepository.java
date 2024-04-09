/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.repositories;

import com.hnqd.dto.BookingRequest;
import com.hnqd.dto.BookingResponse;
import com.hnqd.pojo.Booking;
import com.hnqd.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BookingRepository {

    void addBooking(Booking booking);

    List<BookingResponse> getBookings(int customerId);

    List<BookingResponse> getBookings();

    BookingResponse updateBooking(User staff, int bookingId, String status);

    BookingResponse getBookingById(int id, int customerId);

    BookingResponse getBookingById(int id);
}
