package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.service.AdminBookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/admin/bookings")

@CrossOrigin(origins = "*")

public class AdminBookingController {

    @Autowired
    private AdminBookingService adminBookingService;

    // =========================
    // GET ALL BOOKINGS
    // =========================

    @GetMapping

    public List<Booking> getBookings() {

        return adminBookingService
                .getAllBookings();
    }

    // =========================
    // UPDATE STATUS
    // =========================

    @PutMapping("/{id}")

    public Booking updateStatus(

            @PathVariable Long id,

            @RequestBody Map<String, String> body
    ) {

        return adminBookingService
                .updateStatus(

                        id,

                        body.get("status")
                );
    }
}