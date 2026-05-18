package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.User;
import com.srimathi.project1sb.model.Vehicle;

import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class BookingService {

    @Autowired
    private BookingRepository
            bookingRepository;

    @Autowired
    private VehicleRepository
            vehicleRepository;

    @Autowired
    private UserRepository
            userRepository;

    // =========================
    // BOOK VEHICLE
    // =========================

    public Booking bookVehicle(

            Long vehicleId,

            Long userId,

            String returnDate,

            int familyMembers

    ) {

        Vehicle vehicle =
                vehicleRepository
                        .findById(
                                vehicleId
                        )
                        .orElse(null);

        User user =
                userRepository
                        .findById(
                                userId
                        )
                        .orElse(null);

        if (
                vehicle == null
                        || user == null
        ) {

            return null;
        }

        Booking booking =
                new Booking();

        // =========================
        // USER DETAILS
        // =========================

        booking.setUserId(
                user.getId()
        );

        booking.setVehicleId(
                vehicle.getId()
        );

        booking.setUsername(
                user.getUsername()
        );

        booking.setPhone(
                user.getPhone()
        );

        booking.setAddress(
                user.getAddress()
        );

        // =========================
        // TRIP DETAILS
        // =========================

        booking.setDestination(
                vehicle.getDestination()
        );

        booking.setVehicleType(
                vehicle.getVehicleType()
        );

        booking.setDepartureTime(
                vehicle.getDepartureTime()
        );

        booking.setDepartureDate(
                vehicle.getDepartureDate()
        );

        booking.setReturnDate(
                returnDate
        );

        booking.setFamilyMembers(
                familyMembers
        );

        booking.setPrice(
                vehicle.getPrice()
        );

        booking.setStatus(
                "CONFIRMED"
        );

        // =========================
        // BOOKING MONTH
        // =========================

        String currentMonth =
                LocalDate.now()
                        .getMonth()
                        .getDisplayName(
                                TextStyle.SHORT,
                                Locale.ENGLISH
                        );

        booking.setBookingMonth(
                currentMonth
        );

        // =========================
        // UPDATE VEHICLE STATUS
        // =========================

        vehicle.setBooked(
                true
        );

        vehicleRepository
                .save(vehicle);

        // =========================
        // SAVE BOOKING
        // =========================

        return bookingRepository
                .save(booking);
    }

    // =========================
    // USER BOOKINGS
    // =========================

    public List<Booking>
    getUserBookings(
            Long userId
    ) {

        return bookingRepository
                .findByUserId(
                        userId
                );
    }

    // =========================
    // ADMIN BOOKINGS
    // =========================

    public List<Booking>
    getAllBookings() {

        return bookingRepository
                .findAll();
    }
}