package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public Route addRoute(Route r) {
        r.setSafetyScore(calcSafety(r));
        return repo.save(r);
    }

    public List<Route> search(String source, String dest, double budget) {
        return repo.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, dest)
                .stream()
                .filter(r -> r.getPrice() <= budget && r.getAvailableSeats() > 0)
                .sorted(Comparator.comparing(Route::getPrice))
                .toList();
    }

    private int calcSafety(Route r) {
        if (r.getTransportType().equalsIgnoreCase("Flight")) return 9;
        if (r.getTransportType().equalsIgnoreCase("Train")) return 8;
        return 6;
    }

    public List<Route> all() { return repo.findAll(); }
}