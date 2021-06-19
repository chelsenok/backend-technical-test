package com.tui.proof.service;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightsAvailabilityService {

    private static final ConcurrentHashMap<UUID, Instant> FLIGHTS_AVAILABILITIES_LIFETIME_MAP = new ConcurrentHashMap<>();
    private final FlightService flightService;
    @Value("${api.flight-availability.lifetime-seconds}")
    private int flightAvailabilityLifetimeSeconds;

    public List<FlightsAvailability> searchFlightsAvailabilities(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        List<Flight> flights = flightService.searchFlights(flightsAvailabilityRequest);
        return flights.parallelStream()
                .map(flight -> FlightsAvailability.builder()
                        .availabilityUuid(UUID.randomUUID())
                        .flight(flight)
                        .build()
                )
                .peek(flightsAvailability -> FLIGHTS_AVAILABILITIES_LIFETIME_MAP.put(
                        flightsAvailability.getAvailabilityUuid(),
                        Instant.now()
                ))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 60_000)
    private void cleanFlightsAvailabilitiesLifetimeMap() {
        FLIGHTS_AVAILABILITIES_LIFETIME_MAP.entrySet().parallelStream()
                .filter(entry -> Duration.between(entry.getValue(), Instant.now()).getSeconds() > flightAvailabilityLifetimeSeconds)
                .map(Map.Entry::getKey)
                .forEach(FLIGHTS_AVAILABILITIES_LIFETIME_MAP::remove);
    }
}
