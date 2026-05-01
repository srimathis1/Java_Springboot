package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import com.srimathi.project1sb.repository.UserRepository;
import com.srimathi.project1sb.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final UserRepository userRepo;
    private final RouteRepository routeRepo;
    private final BookingRepository bookingRepo;

    public DashboardService(UserRepository userRepo,
                            RouteRepository routeRepo,
                            BookingRepository bookingRepo) {
        this.userRepo = userRepo;
        this.routeRepo = routeRepo;
        this.bookingRepo = bookingRepo;
    }

    public Map<String, Object> agency() {

        List<Route> routes = routeRepo.findAll();

        Map<String, Object> map = new HashMap<>();

        // ✅ UNIQUE ROUTES
        long uniqueRoutes = routes.stream()
                .map(r -> r.getSource() + "-" + r.getDestination() + "-" + r.getTransportType())
                .distinct()
                .count();

        map.put("routes", uniqueRoutes);

        map.put("totalBookings", 0);

        map.put("topRoute", routes.isEmpty() ? "N/A" :
                routes.get(0).getSource() + " -> " + routes.get(0).getDestination());

        return map;
    }
}