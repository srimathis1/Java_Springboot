package com.srimathi.project1sb.service;

import com.srimathi.project1sb.model.*;
import com.srimathi.project1sb.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    private final BookingRepository bookingRepo;
    private final RouteRepository routeRepo;

    public DashboardService(BookingRepository b, RouteRepository r) {
        this.bookingRepo = b;
        this.routeRepo = r;
    }

    public Map<String,Object> bookings() {

        List<Booking> list = bookingRepo.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("total", list.size());
        map.put("confirmed", list.stream().filter(x->"CONFIRMED".equals(x.getStatus())).count());
        map.put("cancelled", list.stream().filter(x->"CANCELLED".equals(x.getStatus())).count());

        return map;
    }

    public Map<String,Object> agency() {

        List<Route> routes = routeRepo.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("routes", routes.size());

        int totalBookings = routes.stream().mapToInt(Route::getBookingCount).sum();
        map.put("totalBookings", totalBookings);

        Route top = routes.stream().max((a,b)->a.getBookingCount()-b.getBookingCount()).orElse(null);

        if(top!=null)
            map.put("topRoute", top.getSource()+" → "+top.getDestination());

        return map;
    }
}