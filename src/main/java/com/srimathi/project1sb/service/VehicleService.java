package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.Vehicle;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // =========================
    // ADD VEHICLE
    // =========================

    public Vehicle addVehicle(Vehicle vehicle) {

        vehicle.setBooked(false);

        return vehicleRepository.save(vehicle);
    }

    // =========================
    // GET ALL VEHICLES
    // =========================

    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();
    }

    // =========================
    // SEARCH VEHICLES
    // =========================

    public List<Vehicle> searchVehicles(
            String destination
    ) {

        return vehicleRepository
                .findByDestinationContainingIgnoreCase(
                        destination
                );
    }

    // =========================
    // DELETE VEHICLE
    // =========================

    public void deleteVehicle(Long id) {

        vehicleRepository.deleteById(id);
    }
}