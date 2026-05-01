package com.srimathi.project1sb;

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

    // ✅ Add Route
    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {
        return repo.save(route);
    }

    // ✅ Get All Routes
    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return repo.findAll();
    }

    // ✅ Delete Route
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        repo.deleteById(id);
    }
}