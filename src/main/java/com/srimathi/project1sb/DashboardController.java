package com.srimathi.project1sb.controller;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Vehicle;
import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // =====================================
    // DASHBOARD STATS
    // =====================================

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        Map<String, Object> stats = new HashMap<>();

        // =====================================
        // TOTAL USERS
        // =====================================

        long totalUsers = userRepository.count();

        // =====================================
        // TOTAL VEHICLES
        // =====================================

        long totalVehicles = vehicleRepository.count();

        // =====================================
        // TOTAL BOOKINGS
        // =====================================

        long totalBookings = bookingRepository.count();

        // =====================================
        // REVENUE
        // =====================================

        double revenue = bookingRepository
                .findAll()
                .stream()
                .mapToDouble((Booking b) -> b.getPrice())
                .sum();

        // =====================================
        // ACTIVE TRIPS
        // =====================================

        long activeTrips = vehicleRepository
                .findAll()
                .stream()
                .filter((Vehicle v) -> !v.isBooked())
                .count();

        // =====================================
        // COMPLETED BOOKINGS
        // =====================================

        long completedTrips = bookingRepository
                .findAll()
                .stream()
                .filter((Booking b) ->
                        b.getStatus() != null &&
                                b.getStatus().equalsIgnoreCase("CONFIRMED")
                )
                .count();

        // =====================================
        // STORE VALUES
        // =====================================

        stats.put("users", totalUsers);
        stats.put("vehicles", totalVehicles);
        stats.put("bookings", totalBookings);
        stats.put("revenue", revenue);
        stats.put("activeTrips", activeTrips);
        stats.put("completedTrips", completedTrips);

        return stats;
    }
}