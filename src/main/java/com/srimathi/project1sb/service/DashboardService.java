package com.srimathi.project1sb.service;

import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import com.srimathi.project1sb.model.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // =========================
    // TOTAL USERS
    // =========================

    public long getTotalUsers() {

        return userRepository.count();
    }

    // =========================
    // TOTAL BOOKINGS
    // =========================

    public long getTotalBookings() {

        return bookingRepository.count();
    }

    // =========================
    // TOTAL VEHICLES
    // =========================

    public long getTotalVehicles() {

        return vehicleRepository.count();
    }

    // =========================
    // TOTAL REVENUE
    // =========================

    public double getRevenue() {

        List<Booking> bookings = bookingRepository.findAll();

        double total = 0;

        for (Booking booking : bookings) {

            total += booking.getPrice();
        }

        return total;
    }

    // =========================
    // COMPLETED TRIPS
    // =========================

    public long getCompletedTrips() {

        return bookingRepository
                .findAll()
                .stream()
                .filter(
                        b -> "COMPLETED".equalsIgnoreCase(
                                b.getStatus()
                        )
                )
                .count();
    }

    // =========================
    // ACTIVE BOOKINGS
    // =========================

    public long getActiveTrips() {

        return bookingRepository
                .findAll()
                .stream()
                .filter(
                        b -> "BOOKED".equalsIgnoreCase(
                                b.getStatus()
                        )
                )
                .count();
    }
}