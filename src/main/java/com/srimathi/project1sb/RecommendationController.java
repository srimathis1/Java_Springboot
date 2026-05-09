package com.srimathi.project1sb;

import com.srimathi.project1sb.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/recommend")

@CrossOrigin(origins = "*")

public class RecommendationController {

    @Autowired
    private RecommendationService
            recommendationService;

    @GetMapping("/{destination}")

    public List<String> recommend(

            @PathVariable
            String destination
    ) {

        return recommendationService
                .getRecommendations(
                        destination
                );
    }
}