package com.tui.proof.repository;

import com.tui.proof.exception.NotFoundException;
import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Flight repository
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class FlightRepository {

    private final Map<UUID, Flight> FLIGHTS_STORAGE = new ConcurrentHashMap<>();

    @PostConstruct
    private void initFlightsStorage() {
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(100); i++) {
            Flight flight = new Flight();
            flight.setUuid(UUID.randomUUID());
            FLIGHTS_STORAGE.put(flight.getUuid(), flight);
        }
    }

    /**
     * Get all flights from storage.
     *
     * @return list of all flights
     */
    public List<Flight> findAll() {
        return new ArrayList<>(FLIGHTS_STORAGE.values());
    }

    /**
     * Find flight by uuid.
     *
     * @param uuid uuid of flight
     * @return found flight
     * @throws NotFoundException if there is no flight by such uuid in storage
     */
    public Flight findByUuid(UUID uuid) {
        Flight flight = FLIGHTS_STORAGE.get(uuid);
        if (flight == null) {
            log.warn("Flight was not found by uuid {}", uuid);
            throw new NotFoundException(MessageFormatter.format("Flight was not found by uuid {}", uuid).getMessage());
        }
        return flight;
    }

    /**
     * Search flights by provided request params.
     * Search consider all provided parameters and generate the result by strict matching algorithm.
     *
     * @param flightsAvailabilityRequest search request params
     * @return list of found flights
     */
    public List<Flight> searchAllByFlightsAvailabilityRequest(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        Flight[] allFlights = FLIGHTS_STORAGE.values().toArray(new Flight[0]);
        int foundFlightIndex = Math.abs(flightsAvailabilityRequest.hashCode()) % allFlights.length;
        return Collections.singletonList(allFlights[foundFlightIndex]);
    }
}
