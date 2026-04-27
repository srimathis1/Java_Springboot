package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Traveler;
import com.srimathi.project1sb.service.TravelService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/travel")
public class TravelController {

    private final TravelService service;

    public TravelController(TravelService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Traveler addTraveler(@Valid @RequestBody Traveler traveler) {
        return service.addTraveler(traveler);
    }

    @GetMapping("/all")
    public List<Traveler> getAllTravelers() {
        return service.getAllTravelers();
    }
}