package com.srimathi.project1sb.repository;

import com.srimathi.project1sb.model.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository
        extends JpaRepository<Feedback, Long> {

    List<Feedback>
    findByDestination(
            String destination
    );
}