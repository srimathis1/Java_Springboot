package com.srimathi.project1sb.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class RecommendationService {

    public List<String> getRecommendations(
            String destination
    ) {

        Map<String, List<String>> map =
                new HashMap<>();

        map.put(
                "Ooty",

                Arrays.asList(
                        "Kodaikanal",
                        "Coonoor",
                        "Yercaud"
                )
        );

        map.put(
                "Goa",

                Arrays.asList(
                        "Pondicherry",
                        "Kerala",
                        "Mangalore"
                )
        );

        map.put(
                "Madurai",

                Arrays.asList(
                        "Rameshwaram",
                        "Kanyakumari",
                        "Trichy"
                )
        );

        return map.getOrDefault(

                destination,

                Arrays.asList(
                        "Ooty",
                        "Goa",
                        "Madurai"
                )
        );
    }
}