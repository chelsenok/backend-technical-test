package com.tui.proof.service;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final PublisherService publisherService;

    public List<PublishedMessage> getAllFlights() {
        return publisherService.publish(Topic.GET_ALL_FLIGHTS, Collections.emptyMap());
    }

    public List<PublishedMessage> getFlight(UUID uuid) {
        return publisherService.publish(Topic.GET_FLIGHT_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    public List<Flight> searchFlights(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        return new ArrayList<>();
    }
}
