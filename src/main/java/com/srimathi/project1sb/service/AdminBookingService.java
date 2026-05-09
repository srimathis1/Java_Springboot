package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // =========================
    // GET ALL BOOKINGS
    // =========================

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    // =========================
    // UPDATE STATUS
    // =========================

    public Booking updateStatus(
            Long id,
            String status
    ) {

        Booking booking =
                bookingRepository
                        .findById(id)
                        .orElse(null);

        if(booking == null) {

            return null;
        }

        booking.setStatus(status);

        return bookingRepository.save(booking);
    }
}