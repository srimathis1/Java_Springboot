package com.srimathi.project1sb;

import com.srimathi.project1sb.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // ✅ ONLY ONE API (CLEAN)
    @GetMapping("/agency")
    public Map<String, Object> agency() {
        return service.agency();
    }
}