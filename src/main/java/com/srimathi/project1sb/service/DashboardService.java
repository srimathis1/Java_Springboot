package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import com.srimathi.project1sb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final RouteRepository routeRepo;
    private final UserRepository userRepo;

    public DashboardService(RouteRepository routeRepo, UserRepository userRepo) {
        this.routeRepo = routeRepo;
        this.userRepo = userRepo;
    }

    public Map<String, Object> getDashboard() {

        List<Route> routes = routeRepo.findAll();

        Map<String, Object> map = new HashMap<>();

        // ✅ USERS
        map.put("users", userRepo.count());

        // ✅ UNIQUE ROUTES
        long uniqueRoutes = routes.stream()
                .map(r -> r.getSource() + "-" + r.getDestination() + "-" + r.getTransportType())
                .distinct()
                .count();

        map.put("routes", uniqueRoutes);

        // ✅ TRAIN ROUTES
        long trainCount = routes.stream()
                .filter(r -> r.getTransportType().equalsIgnoreCase("train"))
                .map(r -> r.getSource() + "-" + r.getDestination())
                .distinct()
                .count();

        map.put("trainRoutes", trainCount);

        // ✅ FLIGHT ROUTES
        long flightCount = routes.stream()
                .filter(r -> r.getTransportType().equalsIgnoreCase("flight"))
                .map(r -> r.getSource() + "-" + r.getDestination())
                .distinct()
                .count();

        map.put("flightRoutes", flightCount);

        map.put("totalBookings", 0);
        map.put("todayBookings", 0);

        return map;
    }
}