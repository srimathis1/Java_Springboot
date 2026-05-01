package com.srimathi.project1sb.config;

import com.srimathi.project1sb.model.Route;
import com.srimathi.project1sb.repository.RouteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner load(RouteRepository repo) {
        return args -> {

            Route r1 = new Route();
            r1.setSource("Chennai");
            r1.setDestination("Delhi");
            r1.setTransportType("Train");
            r1.setAvailableSeats(5);
            r1.setPrice(2000);

            Route r2 = new Route();
            r2.setSource("Chennai");
            r2.setDestination("Delhi");
            r2.setTransportType("Flight");
            r2.setAvailableSeats(3);
            r2.setPrice(8000);

            repo.save(r1);
            repo.save(r2);
        };
    }
}