package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Vehicle;
import com.srimathi.project1sb.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/vehicles")

@CrossOrigin(origins = "*")

public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // =========================
    // GET ALL VEHICLES
    // =========================

    @GetMapping
    public List<Vehicle> getAllVehicles() {

        return vehicleService.getAllVehicles();
    }

    // =========================
    // ADD VEHICLE
    // =========================

    @PostMapping
    public Vehicle addVehicle(

            @RequestBody Vehicle vehicle

    ) {

        return vehicleService.addVehicle(vehicle);
    }

    // =========================
    // DELETE VEHICLE
    // =========================

    @DeleteMapping("/{id}")
    public void deleteVehicle(

            @PathVariable Long id

    ) {

        vehicleService.deleteVehicle(id);
    }

    // =========================
    // SEARCH VEHICLES
    // =========================

    @GetMapping("/search/{destination}")
    public List<Vehicle> searchVehicles(

            @PathVariable String destination

    ) {

        return vehicleService.searchVehicles(
                destination
        );
    }
}