package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/bookings")

@CrossOrigin(origins = "*")

public class BookingController {

    @Autowired
    private BookingService bookingService;

    // =========================
    // BOOK VEHICLE
    // =========================

    @PostMapping("/book")

    public Booking bookVehicle(

            @RequestParam Long vehicleId,

            @RequestParam String username

    ) {

        return bookingService.bookVehicle(
                vehicleId,
                username
        );
    }

    // =========================
    // GET ALL BOOKINGS
    // =========================

    @GetMapping

    public List<Booking> getAllBookings() {

        return bookingService
                .getAllBookings();
    }

    // =========================
    // GET USER BOOKINGS
    // =========================

    @GetMapping("/user/{username}")

    public List<Booking> getUserBookings(

            @PathVariable String username

    ) {

        return bookingService
                .getUserBookings(username);
    }

    // =========================
    // CANCEL BOOKING
    // =========================

    @DeleteMapping("/cancel/{id}")

    public void cancelBooking(

            @PathVariable Long id

    ) {

        bookingService.cancelBooking(id);
    }
}