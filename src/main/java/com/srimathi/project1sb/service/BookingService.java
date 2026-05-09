package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.User;
import com.srimathi.project1sb.model.Vehicle;

import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // BOOK VEHICLE
    // =========================

    public Booking bookVehicle(
            Long vehicleId,
            String username
    ) {

        Vehicle vehicle = vehicleRepository
                .findById(vehicleId)
                .orElse(null);

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (vehicle == null || user == null) {
            return null;
        }

        Booking booking = new Booking();

        // USER DETAILS

        booking.setUsername(
                user.getUsername()
        );

        booking.setPhone(
                user.getPhone()
        );

        booking.setAddress(
                user.getAddress()
        );

        // TRIP DETAILS

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

        booking.setPrice(
                vehicle.getPrice()
        );

        booking.setStatus("BOOKED");

        return bookingRepository.save(
                booking
        );
    }

    // =========================
    // GET ALL BOOKINGS
    // =========================

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    // =========================
    // USER BOOKINGS
    // =========================

    public List<Booking> getUserBookings(
            String username
    ) {

        return bookingRepository
                .findByUsername(username);
    }

    // =========================
    // CANCEL BOOKING
    // =========================

    public void cancelBooking(Long id) {

        bookingRepository.deleteById(id);
    }
}