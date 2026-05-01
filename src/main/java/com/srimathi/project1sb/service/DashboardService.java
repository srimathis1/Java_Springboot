public Map<String, Object> agency() {

    Map<String, Object> map = new HashMap<>();

    long totalRoutes = routeRepo.count();
    long totalUsers = userRepo.count();
    long totalBookings = bookingRepo.count();

    // today's bookings
    LocalDate today = LocalDate.now();
    long todayBookings = bookingRepo.findAll().stream()
            .filter(b -> b.getCreatedAt() != null &&
                    b.getCreatedAt().toLocalDate().equals(today))
            .count();

    // transport-wise
    List<Route> routes = routeRepo.findAll();

    long trainCount = routes.stream()
            .filter(r -> "Train".equalsIgnoreCase(r.getTransportType()))
            .count();

    long flightCount = routes.stream()
            .filter(r -> "Flight".equalsIgnoreCase(r.getTransportType()))
            .count();

    map.put("totalRoutes", totalRoutes);
    map.put("totalUsers", totalUsers);
    map.put("totalBookings", totalBookings);
    map.put("todayBookings", todayBookings);
    map.put("trainRoutes", trainCount);
    map.put("flightRoutes", flightCount);

    return map;
}