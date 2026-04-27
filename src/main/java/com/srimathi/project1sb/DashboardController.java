package com.srimathi.project1sb;

import com.srimathi.project1sb.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/bookings")
    public Map<String, Object> bookings() {
        return service.bookings();
    }

    @GetMapping("/agency")
    public Map<String, Object> agency() {
        return service.agency();
    }
}