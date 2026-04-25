package com.srimathi.project1sb.model;

import jakarta.persistence.*;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private String transportType;
    private int availableSeats;

    // 🔥 REQUIRED FIELDS
    private double price;
    private int distance;

    public Route() {}

    public Long getId() { return id; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getTransportType() { return transportType; }
    public int getAvailableSeats() { return availableSeats; }

    // 🔥 IMPORTANT (these fix your error)
    public double getPrice() { return price; }
    public int getDistance() { return distance; }

    public void setId(Long id) { this.id = id; }
    public void setSource(String source) { this.source = source; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setTransportType(String transportType) { this.transportType = transportType; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    // 🔥 IMPORTANT setters
    public void setPrice(double price) { this.price = price; }
    public void setDistance(int distance) { this.distance = distance; }
}