package com.srimathi.project1sb.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelerName;
    private Long routeId;

    public Booking() {}

    public Long getId() { return id; }
    public String getTravelerName() { return travelerName; }
    public Long getRouteId() { return routeId; }

    public void setId(Long id) { this.id = id; }
    public void setTravelerName(String travelerName) { this.travelerName = travelerName; }
    public void setRouteId(Long routeId) { this.routeId = routeId; }
}