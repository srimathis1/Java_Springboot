package com.srimathi.project1sb.repository;

import com.srimathi.project1sb.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findBySourceAndDestination(String source, String destination);
}