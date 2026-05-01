package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public Route addRoute(Route route) {
        return repo.save(route);
    }

    public List<Route> getAllRoutes() {
        return repo.findAll();
    }

    public List<Route> smartSearch(String source, String destination, double budget, String preference) {

        List<Route> list = repo.findAll().stream()
                .filter(r -> r.getSource().equalsIgnoreCase(source))
                .filter(r -> r.getDestination().equalsIgnoreCase(destination))
                .filter(r -> r.getPrice() <= budget)
                .collect(Collectors.toList());

        if (preference.equalsIgnoreCase("cheapest")) {
            list.sort(Comparator.comparing(Route::getPrice));
        }

        return list;
    }

    public Route getBestSuggestion(String source, String destination, double budget) {
        List<Route> list = smartSearch(source, destination, budget, "cheapest");
        return list.isEmpty() ? null : list.get(0);
    }
}