package com.srimathi.project1sb.config;

import com.srimathi.project1sb.model.Vehicle;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {

        // PREVENT DUPLICATES

        if(vehicleRepository.count() > 0){
            return;
        }

        // =========================
        // VEHICLE 1
        // =========================

        Vehicle v1 = new Vehicle();

        v1.setDestination("Ooty");
        v1.setVehicleType("Bus");
        v1.setDepartureTime("06:00 AM");
        v1.setDepartureDate("2026-05-10");
        v1.setPrice(15000);
        v1.setBooked(false);

        vehicleRepository.save(v1);

        // =========================
        // VEHICLE 2
        // =========================

        Vehicle v2 = new Vehicle();

        v2.setDestination("Goa");
        v2.setVehicleType("Car");
        v2.setDepartureTime("09:00 AM");
        v2.setDepartureDate("2026-05-12");
        v2.setPrice(22000);
        v2.setBooked(false);

        vehicleRepository.save(v2);

        // =========================
        // VEHICLE 3
        // =========================

        Vehicle v3 = new Vehicle();

        v3.setDestination("Madurai");
        v3.setVehicleType("Bus");
        v3.setDepartureTime("07:30 AM");
        v3.setDepartureDate("2026-05-15");
        v3.setPrice(12000);
        v3.setBooked(false);

        vehicleRepository.save(v3);

        System.out.println("Sample vehicles loaded...");
    }
}