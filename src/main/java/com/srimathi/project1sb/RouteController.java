package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/route")
public class RouteController {

    private final RouteRepository repo;

    public RouteController(RouteRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {
        return repo.save(route);
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return repo.findAll();
    }
}