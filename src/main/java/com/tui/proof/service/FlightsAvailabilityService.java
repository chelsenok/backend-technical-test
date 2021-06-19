package com.tui.proof.service;

import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.helpers.MessageFormatter;
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

    public void assertFlightAvailability(UUID availabilityUuid) {
        if (!FLIGHTS_AVAILABILITIES_LIFETIME_MAP.containsKey(availabilityUuid)) {
            throw new ForbiddenException(MessageFormatter.format("Flight availability {} is not available", availabilityUuid).getMessage());
        }
    }

    @Scheduled(fixedRate = 30_000)
    private void cleanFlightsAvailabilitiesLifetimeMap() {
        FLIGHTS_AVAILABILITIES_LIFETIME_MAP.entrySet().parallelStream()
                .filter(entry -> Duration.between(entry.getValue(), Instant.now()).getSeconds() > flightAvailabilityLifetimeSeconds)
                .map(Map.Entry::getKey)
                .forEach(FLIGHTS_AVAILABILITIES_LIFETIME_MAP::remove);
    }
}
