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

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {

    private final PublisherService publisherService;

    public List<PublishedMessage> publishGetAllFlights() {
        return publisherService.publish(Topic.GET_ALL_FLIGHTS, Collections.emptyMap());
    }

    public List<PublishedMessage> publishGetFlight(UUID uuid) {
        return publisherService.publish(Topic.GET_FLIGHT_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    @Stub
    public List<Flight> getAllFlights() {
        log.warn("Stubbing for getAllFlights");
        return new ArrayList<>();
    }

    @Stub
    public Flight getFlight(UUID uuid) {
        log.warn("Stubbing for getFlight");
        return new Flight();
    }

    @Stub
    public List<Flight> searchFlights(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        log.warn("Stubbing for searchFlights");
        return new ArrayList<>();
    }
}
