package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Vehicle;

import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Booking> bookings =
                bookingRepository.findAll();

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

        LocalDate today =
                LocalDate.now();

        return bookingRepository
                .findAll()
                .stream()

                .filter(booking -> {

                    try {

                        LocalDate returnDate =
                                LocalDate.parse(
                                        booking.getReturnDate()
                                );

                        return
                                returnDate.isBefore(today)
                                        ||
                                        returnDate.isEqual(today);

                    } catch (Exception e) {

                        return false;
                    }
                })

                .count();
    }

    // =========================
    // ACTIVE TRIPS
    // =========================

    public long getActiveTrips() {

        LocalDate today =
                LocalDate.now();

        return bookingRepository
                .findAll()
                .stream()

                .filter(booking -> {

                    try {

                        LocalDate returnDate =
                                LocalDate.parse(
                                        booking.getReturnDate()
                                );

                        return
                                returnDate.isAfter(today);

                    } catch (Exception e) {

                        return false;
                    }
                })

                .count();
    }

    // =========================
    // VEHICLE BREAKDOWN
    // =========================

    public Map<String, Long>
    getVehicleBreakdown() {

        List<Vehicle> vehicles =
                vehicleRepository.findAll();

        long cars =
                vehicles.stream()
                        .filter(v ->
                                "Car".equalsIgnoreCase(
                                        v.getVehicleType()
                                )
                        )
                        .count();

        long buses =
                vehicles.stream()
                        .filter(v ->
                                "Bus".equalsIgnoreCase(
                                        v.getVehicleType()
                                )
                        )
                        .count();

        long others =
                vehicles.size()
                        - cars
                        - buses;

        Map<String, Long>
                data =
                new HashMap<>();

        data.put("cars", cars);

        data.put("buses", buses);

        data.put("others", others);

        return data;
    }
}