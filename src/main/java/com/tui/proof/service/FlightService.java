package com.tui.proof.service;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.utils.Stub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * Stubbed method
     *
     * @return stub
     */
    @Stub
    public List<Flight> getAllFlights() {
        log.warn("Stubbing for getAllFlights");
        return new ArrayList<>();
    }

    /**
     * Stubbed method
     *
     * @return stub
     */
    @Stub
    public Flight getFlight(UUID uuid) {
        log.warn("Stubbing for getFlight");
        return new Flight();
    }

    /**
     * Stubbed method
     *
     * @return stub
     */
    @Stub
    public List<Flight> searchFlights(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        log.warn("Stubbing for searchFlights");
        return new ArrayList<>();
    }
}
