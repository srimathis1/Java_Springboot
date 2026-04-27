package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    // ADD ROUTE
    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {
        return service.addRoute(route);
    }

    // GET ALL ROUTES
    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return service.getAllRoutes();
    }

    // SEARCH
    @GetMapping("/smart-search")
    public List<Route> smartSearch(@RequestParam String source,
                                   @RequestParam String destination,
                                   @RequestParam double budget,
                                   @RequestParam String preference) {
        return service.smartSearch(source, destination, budget, preference);
    }

    // 🔥 AI SUGGESTION
    @GetMapping("/suggest")
    public String suggest(@RequestParam String source,
                          @RequestParam String destination,
                          @RequestParam double budget) {
        return service.getBestSuggestion(source, destination, budget);
    }
}