package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.RouteRepository;

import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RouteRepository routeRepo;

    public BookingService(BookingRepository bookingRepo, RouteRepository routeRepo) {
        this.bookingRepo = bookingRepo;
        this.routeRepo = routeRepo;
    }

    public Booking createBooking(Booking booking) {

        Route route = routeRepo.findById(booking.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (route.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // ✅ decrease seats
        route.setAvailableSeats(route.getAvailableSeats() - 1);
        routeRepo.save(route);

        booking.setStatus("CONFIRMED");

        return bookingRepo.save(booking);
    }
}