package com.srimathi.project1sb.model;

public class Traveler {

    private String name;
    private int age;
    private String destination;

    public Traveler() {}  // IMPORTANT (needed for JSON)

    public Traveler(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDestination() { return destination; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setDestination(String destination) { this.destination = destination; }
}