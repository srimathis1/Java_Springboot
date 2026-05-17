package com.srimathi.project1sb;

import com.srimathi.project1sb.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService
            dashboardService;

    @GetMapping("/stats")
    public Map<String, Object>
    getStats() {

        Map<String, Object>
                stats =
                new HashMap<>();

        stats.put(
                "users",
                dashboardService
                        .getTotalUsers()
        );

        stats.put(
                "vehicles",
                dashboardService
                        .getTotalVehicles()
        );

        stats.put(
                "bookings",
                dashboardService
                        .getTotalBookings()
        );

        stats.put(
                "revenue",
                dashboardService
                        .getRevenue()
        );

        stats.put(
                "activeTrips",
                dashboardService
                        .getActiveTrips()
        );

        stats.put(
                "completedTrips",
                dashboardService
                        .getCompletedTrips()
        );

        stats.put(
                "vehicleBreakdown",
                dashboardService
                        .getVehicleBreakdown()
        );

        return stats;
    }
}