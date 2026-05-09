package com.srimathi.project1sb.repository;

import com.srimathi.project1sb.model.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    List<Vehicle>
    findByDestinationContainingIgnoreCase(
            String destination
    );
}