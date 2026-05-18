package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Feedback;
import com.srimathi.project1sb.model.Vehicle;
import com.srimathi.project1sb.repository.BookingRepository;
import com.srimathi.project1sb.repository.FeedbackRepository;
import com.srimathi.project1sb.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/dashboard")
    public Map<String, Object>
    dashboardAnalytics() {

        Map<String, Object>
                response =
                new HashMap<>();

        List<Booking> bookings =
                bookingRepository.findAll();

        List<Vehicle> vehicles =
                vehicleRepository.findAll();

        List<Feedback> feedbacks =
                feedbackRepository.findAll();

        // ====================
        // TOTALS
        // ====================

        response.put(
                "totalTrips",
                vehicles.size()
        );

        response.put(
                "totalBookings",
                bookings.size()
        );

        response.put(
                "totalFeedbacks",
                feedbacks.size()
        );

        // ====================
        // MOST BOOKED PLACE
        // ====================

        Map<String, Integer>
                destinationMap =
                new HashMap<>();

        for (
                Booking booking
                : bookings
        ) {

            String destination =
                    booking
                            .getDestination();

            if (
                    destination != null
            ) {

                destinationMap.put(

                        destination,

                        destinationMap
                                .getOrDefault(
                                        destination,
                                        0
                                ) + 1
                );
            }
        }

        String mostBooked =
                "No Data";

        int max = 0;

        for (
                Map.Entry<String,
                        Integer> entry
                : destinationMap.entrySet()
        ) {

            if (
                    entry.getValue()
                            > max
            ) {

                max =
                        entry.getValue();

                mostBooked =
                        entry.getKey();
            }
        }

        response.put(
                "mostBookedPlace",
                mostBooked
        );

        // ====================
        // MONTH GRAPH DATA
        // ====================

        Map<String, Integer>
                monthData =
                new LinkedHashMap<>();

        monthData.put("Jan", 0);
        monthData.put("Feb", 0);
        monthData.put("Mar", 0);
        monthData.put("Apr", 0);
        monthData.put("May", 0);
        monthData.put("Jun", 0);
        monthData.put("Jul", 0);
        monthData.put("Aug", 0);
        monthData.put("Sep", 0);
        monthData.put("Oct", 0);
        monthData.put("Nov", 0);
        monthData.put("Dec", 0);

        for (
                Booking booking
                : bookings
        ) {

            String month =
                    booking
                            .getBookingMonth();

            if (
                    month != null
                            &&
                            monthData.containsKey(
                                    month
                            )
            ) {

                monthData.put(

                        month,

                        monthData
                                .get(month)
                                + 1
                );
            }
        }

        response.put(
                "monthlyBookings",
                monthData
        );

        // ====================
        // CHEAPEST PLACES
        // ====================

        vehicles.sort(
                Comparator.comparingDouble(
                        Vehicle::getPrice
                )
        );

        List<Map<String,
                Object>>
                cheapestPlaces =
                new ArrayList<>();

        for (
                int i = 0;
                i < Math.min(
                        3,
                        vehicles.size()
                );
                i++
        ) {

            Vehicle v =
                    vehicles.get(i);

            Map<String,
                    Object> place =
                    new HashMap<>();

            place.put(
                    "destination",
                    v.getDestination()
            );

            place.put(
                    "price",
                    v.getPrice()
            );

            cheapestPlaces
                    .add(place);
        }

        response.put(
                "cheapestPlaces",
                cheapestPlaces
        );

        // ====================
        // TOP RATED PLACE
        // ====================

        Map<String,
                List<Integer>>
                ratingMap =
                new HashMap<>();

        for (
                Feedback feedback
                : feedbacks
        ) {

            if (
                    feedback
                            .getDestination()
                            != null
            ) {

                ratingMap
                        .putIfAbsent(

                                feedback
                                        .getDestination(),

                                new ArrayList<>()
                        );

                ratingMap
                        .get(
                                feedback
                                        .getDestination()
                        )

                        .add(
                                feedback
                                        .getRating()
                        );
            }
        }

        String topRated =
                "No Ratings";

        double highest =
                0;

        for (
                Map.Entry<String,
                        List<Integer>>
                        entry
                : ratingMap.entrySet()
        ) {

            double avg =
                    entry
                            .getValue()
                            .stream()
                            .mapToInt(
                                    Integer::intValue
                            )
                            .average()
                            .orElse(0);

            if (
                    avg > highest
            ) {

                highest =
                        avg;

                topRated =
                        entry.getKey();
            }
        }

        response.put(
                "topRatedPlace",
                topRated
        );

        return response;
    }
}