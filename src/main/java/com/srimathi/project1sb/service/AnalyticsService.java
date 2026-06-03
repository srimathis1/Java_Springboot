package com.srimathi.project1sb.service;

import com.srimathi.project1sb.repository.AnalyticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository
            analyticsRepository;

    public Map<String, Object>
    getDashboardAnalytics() {

        Map<String, Object>
                analytics =
                new HashMap<>();

        analytics.put(
                "monthlyBookings",
                analyticsRepository
                        .getMonthlyBookings()
        );

        analytics.put(
                "bookingsByDestination",
                analyticsRepository
                        .getBookingsByDestination()
        );

        analytics.put(
                "ratingsByDestination",
                analyticsRepository
                        .getRatingsByDestination()
        );

        return analytics;
    }
}