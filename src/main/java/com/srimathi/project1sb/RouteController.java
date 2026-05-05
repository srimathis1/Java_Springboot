package com.srimathi.project1sb.controller;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route")
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {

    private final RouteRepository repo;

    public RouteController(RouteRepository repo) {
        this.repo = repo;
    }

    // ✅ ADD OR UPDATE (NO DUPLICATES)
    @PostMapping("/add")
    public Route addOrUpdate(@RequestBody Route route) {

        Optional<Route> existing = repo
                .findBySourceIgnoreCaseAndDestinationIgnoreCaseAndTransportTypeIgnoreCase(
                        route.getSource(),
                        route.getDestination(),
                        route.getTransportType()
                );

        if (existing.isPresent()) {
            Route r = existing.get();

            // UPDATE existing
            r.setPrice(route.getPrice());
            r.setAvailableSeats(r.getAvailableSeats() + route.getAvailableSeats());

            return repo.save(r);
        }

        return repo.save(route);
    }

    // GET ALL
    @GetMapping("/all")
    public List<Route> getAll() {
        return repo.findAll();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}