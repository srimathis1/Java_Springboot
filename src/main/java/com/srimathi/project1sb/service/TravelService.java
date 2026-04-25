package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Traveler;
import com.srimathi.project1sb.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    private final TravelerRepository repository;

    public TravelService(TravelerRepository repository) {
        this.repository = repository;
    }

    public Traveler addTraveler(Traveler traveler) {
        return repository.save(traveler);
    }

    public List<Traveler> getAllTravelers() {
        return repository.findAll();
    }
}