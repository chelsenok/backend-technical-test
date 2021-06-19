package com.tui.proof.controller;

import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.service.FlightService;
import com.tui.proof.service.FlightsAvailabilityService;
import com.tui.proof.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "${api.v1}/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final ValidationService validationService;
    private final FlightsAvailabilityService flightsAvailabilityService;

    @Value("${api.v1.messages}")
    private String messagesLocation;

    @GetMapping
    public ResponseEntity<List<PublishedMessage>> getAllFlights() {
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(flightService.getAllFlights());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<List<PublishedMessage>> getFlight(@PathVariable UUID uuid) {
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(flightService.getFlight(uuid));
    }

    @PostMapping("/search_availabilities")
    public ResponseEntity<List<FlightsAvailability>> searchFlightsAvailabilities(
            @RequestBody FlightsAvailabilityRequest flightsAvailabilityRequest) {
        validationService.validate(flightsAvailabilityRequest);
        return ResponseEntity.ok(flightsAvailabilityService.searchFlightsAvailabilities(flightsAvailabilityRequest));
    }
}
