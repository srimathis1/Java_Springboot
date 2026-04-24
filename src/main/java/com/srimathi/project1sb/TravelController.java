package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Traveler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelController {

    private List<Traveler> travelers = new ArrayList<>();

    // ADD traveler
    @PostMapping("/add")
    public Traveler addTraveler(@RequestBody Traveler traveler) {
        travelers.add(traveler);
        return traveler;
    }

    // GET all travelers
    @GetMapping("/all")
    public List<Traveler> getAllTravelers() {
        return travelers;
    }
}