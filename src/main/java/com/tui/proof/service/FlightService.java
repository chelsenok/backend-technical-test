package com.tui.proof.service;

import com.tui.proof.exception.NotFoundException;
import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Flight service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {

    private final PublisherService publisherService;
    private final FlightRepository flightRepository;

    /**
     * Publish GET_ALL_FLIGHTS message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishGetAllFlights() {
        return publisherService.publish(Topic.GET_ALL_FLIGHTS, Collections.emptyMap());
    }

    /**
     * Publish GET_FLIGHT_BY_UUID message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishGetFlight(UUID uuid) {
        return publisherService.publish(Topic.GET_FLIGHT_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    /**
     * Get all flights from database
     *
     * @return all flights from database
     */
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Find flight by identity
     *
     * @return found flight
     * @throws NotFoundException if there is no flight by provided uuid
     */
    public Flight getFlight(UUID uuid) {
        return flightRepository.findByUuid(uuid);
    }

    /**
     * Search for a flights by provided request params
     *
     * @return list of flights which are fit to the search params
     */
    public List<Flight> searchFlights(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        return flightRepository.searchAllByFlightsAvailabilityRequest(flightsAvailabilityRequest);
    }
}
