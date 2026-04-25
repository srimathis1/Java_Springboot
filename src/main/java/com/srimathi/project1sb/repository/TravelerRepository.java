package com.srimathi.project1sb.repository;

import com.srimathi.project1sb.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}