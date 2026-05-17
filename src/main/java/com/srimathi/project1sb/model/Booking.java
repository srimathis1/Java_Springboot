package com.srimathi.project1sb.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    // =========================
    // RELATIONS
    // =========================

    private Long userId;

    private Long vehicleId;

    // =========================
    // USER DETAILS
    // =========================

    private String username;

    private String phone;

    private String address;

    // =========================
    // TRIP DETAILS
    // =========================

    private String destination;

    private String vehicleType;

    private String departureTime;

    private String departureDate;

    private String returnDate;

    private int familyMembers;

    private double price;

    private String status;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId
    ) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(
            Long vehicleId
    ) {
        this.vehicleId =
                vehicleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username
    ) {
        this.username =
                username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(
            String phone
    ) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(
            String address
    ) {
        this.address =
                address;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(
            String destination
    ) {
        this.destination =
                destination;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(
            String vehicleType
    ) {
        this.vehicleType =
                vehicleType;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(
            String departureTime
    ) {
        this.departureTime =
                departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(
            String departureDate
    ) {
        this.departureDate =
                departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(
            String returnDate
    ) {
        this.returnDate =
                returnDate;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(
            int familyMembers
    ) {
        this.familyMembers =
                familyMembers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(
            double price
    ) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status
    ) {
        this.status = status;
    }
}