package com.srimathi.project1sb;

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

    // ✅ ADD OR UPDATE (NO DUPLICATES EVEN WITH CASE)
    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {

        // 🔥 normalize input (IMPORTANT)
        String source = route.getSource().toLowerCase().trim();
        String destination = route.getDestination().toLowerCase().trim();
        String type = route.getTransportType().toLowerCase().trim();

        Optional<Route> existing = repo.findBySourceAndDestinationAndTransportType(
                source, destination, type
        );

        if (existing.isPresent()) {
            Route r = existing.get();
            r.setPrice(route.getPrice());
            r.setAvailableSeats(route.getAvailableSeats());
            return repo.save(r);
        }

        route.setSource(source);
        route.setDestination(destination);
        route.setTransportType(type);

        return repo.save(route);
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}