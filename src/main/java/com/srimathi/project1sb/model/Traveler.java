package com.srimathi.project1sb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Destination cannot be empty")
    private String destination;

    public Traveler() {}

    public Long getId() { return id; }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDestination() { return destination; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setDestination(String destination) { this.destination = destination; }
}