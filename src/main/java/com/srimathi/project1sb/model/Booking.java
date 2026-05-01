package com.srimathi.project1sb.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelerName;
    private Long routeId;
    private String status;

    // getters setters
    public Long getId() { return id; }

    public String getTravelerName() { return travelerName; }
    public void setTravelerName(String travelerName) { this.travelerName = travelerName; }

    public Long getRouteId() { return routeId; }
    public void setRouteId(Long routeId) { this.routeId = routeId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}