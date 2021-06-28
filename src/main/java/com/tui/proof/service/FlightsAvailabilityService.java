package com.tui.proof.service;

import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.NotFoundException;
import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
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

/**
 * Flight availability service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlightsAvailabilityService {

    private static final ConcurrentHashMap<UUID, Pair<FlightsAvailability, Instant>> FLIGHTS_AVAILABILITIES_LIFETIME_MAP = new ConcurrentHashMap<>();
    private final FlightService flightService;
    @Value("${api.flight-availability.lifetime-seconds}")
    private int flightAvailabilityLifetimeSeconds;

    /**
     * Search availabilities by provided request params
     *
     * @param flightsAvailabilityRequest search params
     * @return list of found availabilities
     */
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
                        Pair.of(flightsAvailability, Instant.now())
                ))
                .peek(flightsAvailability -> log.info("Found availability: {}", flightsAvailability))
                .collect(Collectors.toList());
    }

    /**
     * Assert that availability is still valid
     *
     * @param availability identity of availability
     * @throws ForbiddenException if availability is not valid
     */
    public void assertFlightAvailability(FlightsAvailability availability) {
        if (!FLIGHTS_AVAILABILITIES_LIFETIME_MAP.containsKey(availability.getAvailabilityUuid())) {
            log.error("Flight availability was not found by {}", availability);
            throw new ForbiddenException(MessageFormatter.format("Flight availability {} is not available", availability).getMessage());
        }
    }

    /**
     * Scheduled procedure for cleaning flight availabilities by lifetime
     */
    @Scheduled(fixedRate = 30_000)
    private void cleanFlightsAvailabilitiesLifetimeMap() {
        log.info("Cleaning flight availabilities storage....");
        FLIGHTS_AVAILABILITIES_LIFETIME_MAP.entrySet().parallelStream()
                .filter(entry -> Duration.between(entry.getValue().getRight(), Instant.now()).getSeconds() > flightAvailabilityLifetimeSeconds)
                .map(Map.Entry::getKey)
                .peek(uuid -> log.info("Removing {} availability from storage", uuid))
                .forEach(FLIGHTS_AVAILABILITIES_LIFETIME_MAP::remove);
    }

    /**
     * Get flight availability by identifier
     *
     * @param availabilityUuid identity of availability
     * @return found availability
     * @throws NotFoundException if availability was not found by provided identity
     */
    public FlightsAvailability getFlightAvailability(UUID availabilityUuid) {
        FlightsAvailability availability = FLIGHTS_AVAILABILITIES_LIFETIME_MAP.get(availabilityUuid).getLeft();
        if (availability == null) {
            log.warn("FlightsAvailability was not found by uuid {}", availabilityUuid);
            throw new NotFoundException(MessageFormatter.format("FlightsAvailability was not found by uuid {}", availabilityUuid).getMessage());
        }
        return availability;
    }
}
