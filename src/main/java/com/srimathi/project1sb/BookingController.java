package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public String createBooking(@RequestBody Booking booking) {
        return service.createBooking(booking);
    }
}