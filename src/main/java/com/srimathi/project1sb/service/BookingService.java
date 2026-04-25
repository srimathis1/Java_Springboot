package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final RouteRepository routeRepository;

    public BookingService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public String createBooking(Booking booking) {

        Route route = routeRepository.findById(booking.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (route.getAvailableSeats() <= 0) {
            return "❌ No seats available";
        }

        route.setAvailableSeats(route.getAvailableSeats() - 1);
        routeRepository.save(route);

        return "✅ Booking confirmed";
    }
}