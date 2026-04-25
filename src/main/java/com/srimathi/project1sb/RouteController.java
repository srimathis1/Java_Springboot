package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {
        return service.addRoute(route);
    }

    // 🔥 UPDATED SEARCH
    @GetMapping("/smart-search")
    public List<Route> smartSearch(@RequestParam String source,
                                   @RequestParam String destination,
                                   @RequestParam double budget,
                                   @RequestParam String preference) {
        return service.smartSearch(source, destination, budget, preference);
    }

    // 🔥 BEST ROUTE API
    @GetMapping("/best")
    public Route bestRoute(@RequestParam String source,
                           @RequestParam String destination,
                           @RequestParam double budget) {
        return service.getBestRoute(source, destination, budget);
    }
}