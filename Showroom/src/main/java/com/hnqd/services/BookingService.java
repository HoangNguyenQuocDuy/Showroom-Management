/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.services;

import com.hnqd.dto.BookingRequest;
import com.hnqd.dto.BookingResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BookingService {

    void addBooking(BookingRequest bookingRequest, String username) throws Exception;

    List<BookingResponse> getBookings(int customerId);

    List<BookingResponse> getBookings();

    void updateBooking(String username, int bookingId, String status) throws Exception;

    BookingResponse getBookingById(int id, int customerId);

    BookingResponse getBookingById(int id);

}
