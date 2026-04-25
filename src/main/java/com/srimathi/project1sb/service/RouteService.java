package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository repository;

    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }

    public Route addRoute(Route route) {
        return repository.save(route);
    }

    // 🔥 SMART SEARCH WITH NEW FEATURES
    public List<Route> smartSearch(String source, String destination, double budget, String preference) {

        List<Route> routes = repository.findBySourceAndDestination(source, destination);

        List<Route> filtered = routes.stream()
                .filter(r -> r.getAvailableSeats() > 0) // 🔥 Feature 2
                .filter(r -> r.getPrice() <= budget)
                .filter(this::smartTransportLogic)
                .collect(Collectors.toList());

        // 🔥 Feature 1: Sorting
        if (preference.equalsIgnoreCase("cheapest")) {
            filtered.sort(Comparator.comparing(Route::getPrice));
        } else if (preference.equalsIgnoreCase("fastest")) {
            filtered.sort(Comparator.comparing(Route::getDistance));
        }

        return filtered;
    }

    // 🔥 Feature 3: Best suggestion
    public Route getBestRoute(String source, String destination, double budget) {

        List<Route> routes = smartSearch(source, destination, budget, "cheapest");

        return routes.isEmpty() ? null : routes.get(0);
    }

    // 🔥 Transport logic (same as before)
    private boolean smartTransportLogic(Route route) {

        int distance = route.getDistance();
        String type = route.getTransportType();

        if (distance > 3000) {
            return type.equalsIgnoreCase("Flight");
        }

        if (distance < 300) {
            return type.equalsIgnoreCase("Bus");
        }

        if (distance >= 300 && distance <= 1500) {
            return type.equalsIgnoreCase("Train");
        }

        if (distance > 1500) {
            return type.equalsIgnoreCase("Flight") || type.equalsIgnoreCase("Train");
        }

        return true;
    }
}