package com.srimathi.project1sb.controller;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import com.srimathi.project1sb.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    private final RouteRepository routeRepo;
    private final UserRepository userRepo;

    public DashboardController(RouteRepository routeRepo, UserRepository userRepo) {
        this.routeRepo = routeRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/admin")
    public Map<String, Object> getStats() {

        List<Route> routes = routeRepo.findAll();

        long totalRoutes = routes.size();
        long users = userRepo.count();

        long trainRoutes = routes.stream()
                .filter(r -> r.getTransportType() != null &&
                        r.getTransportType().equalsIgnoreCase("train"))
                .count();

        long flightRoutes = routes.stream()
                .filter(r -> r.getTransportType() != null &&
                        r.getTransportType().equalsIgnoreCase("flight"))
                .count();

        Map<String, Object> map = new HashMap<>();

        map.put("users", users);
        map.put("routes", totalRoutes);
        map.put("totalBookings", 0);
        map.put("todayBookings", 0);
        map.put("trainRoutes", trainRoutes);
        map.put("flightRoutes", flightRoutes);

        return map;
    }
}