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
    private BookingService
            bookingService;

    @PostMapping("/book")
    public Booking bookVehicle(

            @RequestParam
            Long vehicleId,

            @RequestParam
            Long userId,

            @RequestParam
            String returnDate,

            @RequestParam
            int familyMembers

    ) {

        return bookingService
                .bookVehicle(
                        vehicleId,
                        userId,
                        returnDate,
                        familyMembers
                );
    }

    @GetMapping("/user/{userId}")
    public List<Booking>
    getUserBookings(

            @PathVariable
            Long userId
    ) {

        return bookingService
                .getUserBookings(
                        userId
                );
    }

    @GetMapping
    public List<Booking>
    getAllBookings() {

        return bookingService
                .getAllBookings();
    }
}