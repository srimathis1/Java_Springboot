package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping("/create")
    public Booking create(@RequestBody Booking booking) {
        return service.createBooking(booking);
    }

    // ✅ HISTORY
    @GetMapping("/history")
    public List<Booking> history(@RequestParam String travelerName) {
        return service.getBookings(travelerName);
    }

    // ✅ CANCEL
    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {
        return service.cancelBooking(id);
    }
}