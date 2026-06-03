package com.srimathi.project1sb.repository;

import com.srimathi.project1sb.model.Booking;
import com.srimathi.project1sb.model.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AnalyticsRepository {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // ======================
    // TOTAL TRIPS
    // ======================

    public long getTotalTrips() {

        return vehicleRepository.count();
    }

    // ======================
    // TOTAL BOOKINGS
    // ======================

    public long getTotalBookings() {

        return bookingRepository.count();
    }

    // ======================
    // TOTAL FEEDBACKS
    // ======================

    public long getTotalFeedbacks() {

        return feedbackRepository.count();
    }

    // ======================
    // MOST BOOKED PLACE
    // ======================

    public String getMostBookedPlace() {

        List<Booking> bookings =
                bookingRepository.findAll();

        if (bookings.isEmpty()) {

            return "No Bookings";
        }

        Map<String, Long> bookingCounts =

                bookings.stream()

                        .filter(
                                booking ->
                                        booking.getDestination()
                                                != null
                        )

                        .collect(

                                Collectors.groupingBy(

                                        Booking
                                                ::getDestination,

                                        Collectors
                                                .counting()
                                )
                        );

        return bookingCounts
                .entrySet()
                .stream()

                .max(
                        Map.Entry
                                .comparingByValue()
                )

                .map(
                        Map.Entry::getKey
                )

                .orElse(
                        "No Data"
                );
    }

    // ======================
    // BOOKINGS BY DESTINATION
    // ======================

    public List<Map<String, Object>>
    getBookingsByDestination() {

        List<Booking> bookings =
                bookingRepository.findAll();

        Map<String, Long>
                bookingCounts =

                bookings.stream()

                        .filter(

                                booking ->

                                        booking.getDestination()
                                                != null
                        )

                        .collect(

                                Collectors.groupingBy(

                                        Booking
                                                ::getDestination,

                                        Collectors
                                                .counting()
                                )
                        );

        List<Map<String, Object>>
                result =
                new ArrayList<>();

        bookingCounts.forEach(

                (
                        destination,
                        count
                ) -> {

                    Map<String, Object>
                            map =
                            new HashMap<>();

                    map.put(
                            "destination",
                            destination
                    );

                    map.put(
                            "count",
                            count
                    );

                    result.add(map);
                }
        );

        return result;
    }

    // ======================
    // RATINGS BY DESTINATION
    // ======================

    public List<Map<String, Object>>
    getRatingsByDestination() {

        List<Feedback>
                feedbacks =
                feedbackRepository.findAll();

        Map<String, Double>
                avgRatings =

                feedbacks.stream()

                        .filter(

                                feedback ->

                                        feedback.getDestination()
                                                != null

                                                &&

                                                feedback.getRating()
                                                        != null
                        )

                        .collect(

                                Collectors.groupingBy(

                                        Feedback
                                                ::getDestination,

                                        Collectors
                                                .averagingDouble(

                                                        Feedback
                                                                ::getRating
                                                )
                                )
                        );

        List<Map<String, Object>>
                result =
                new ArrayList<>();

        avgRatings.forEach(

                (
                        destination,
                        rating
                ) -> {

                    Map<String, Object>
                            map =
                            new HashMap<>();

                    map.put(
                            "name",
                            destination
                    );

                    map.put(
                            "value",
                            rating
                    );

                    result.add(map);
                }
        );

        return result;
    }

    // ======================
    // MONTHLY BOOKINGS
    // ======================

    public Map<String, Long>
    getMonthlyBookings() {

        List<Booking>
                bookings =
                bookingRepository.findAll();

        Map<String, Long>
                monthlyBookings =
                new LinkedHashMap<>();

        monthlyBookings.put("Jan", 0L);
        monthlyBookings.put("Feb", 0L);
        monthlyBookings.put("Mar", 0L);
        monthlyBookings.put("Apr", 0L);
        monthlyBookings.put("May", 0L);
        monthlyBookings.put("Jun", 0L);
        monthlyBookings.put("Jul", 0L);
        monthlyBookings.put("Aug", 0L);
        monthlyBookings.put("Sep", 0L);
        monthlyBookings.put("Oct", 0L);
        monthlyBookings.put("Nov", 0L);
        monthlyBookings.put("Dec", 0L);

        for (Booking booking : bookings) {

            try {

                if (
                        booking
                                .getDepartureDate()
                                == null
                ) {

                    continue;
                }

                LocalDate date =
                        LocalDate.parse(

                                booking
                                        .getDepartureDate(),

                                DateTimeFormatter
                                        .ofPattern(
                                                "yyyy-MM-dd"
                                        )
                        );

                String month =
                        date.getMonth()
                                .toString()
                                .substring(
                                        0,
                                        3
                                );

                month =
                        month.substring(
                                0,
                                1
                        )
                                +
                                month.substring(
                                                1
                                        )
                                        .toLowerCase();

                monthlyBookings.put(

                        month,

                        monthlyBookings.get(
                                month
                        ) + 1
                );

            } catch (
                    Exception e
            ) {

                System.out.println(
                        "Invalid Date"
                );
            }
        }

        return monthlyBookings;
    }
}