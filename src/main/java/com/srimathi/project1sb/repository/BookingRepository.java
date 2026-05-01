package com.srimathi.project1sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srimathi.project1sb.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}