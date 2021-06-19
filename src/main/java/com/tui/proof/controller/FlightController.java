package com.tui.proof.controller;

import com.tui.proof.controller.api.FlightApi;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.service.FlightService;
import com.tui.proof.service.FlightsAvailabilityService;
import com.tui.proof.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Flight REST API
 */
@Slf4j
@RestController
@RequestMapping(path = "${api.v1}/flights")
@RequiredArgsConstructor
public class FlightController implements FlightApi {

    private final FlightService flightService;
    private final ValidationService validationService;
    private final FlightsAvailabilityService flightsAvailabilityService;

    @Value("${api.v1.messages}")
    private String messagesLocation;

    /**
     * Get all flights query
     *
     * @return list of published messages to pub/sub
     */
    @GetMapping
    public ResponseEntity<List<PublishedMessage>> getAllFlights() {
        log.info("Get all flights query...");
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(flightService.publishGetAllFlights());
    }

    /**
     * Get flight by identity query
     *
     * @param uuid flight identity
     * @return list of published messages to pub/sub
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<List<PublishedMessage>> getFlight(@PathVariable UUID uuid) {
        log.info("Get flight by uuid query: {}", uuid);
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(flightService.publishGetFlight(uuid));
    }

    /**
     * Search flights query
     *
     * @param flightsAvailabilityRequest flight availability request body
     * @return list of found flight availabilities
     */
    @PostMapping("/search_availabilities")
    public ResponseEntity<List<FlightsAvailability>> searchFlightsAvailabilities(
            @RequestBody FlightsAvailabilityRequest flightsAvailabilityRequest) {
        log.info("Search flight availabilities query: {}", flightsAvailabilityRequest);
        validationService.validate(flightsAvailabilityRequest);
        return ResponseEntity.ok(flightsAvailabilityService.searchFlightsAvailabilities(flightsAvailabilityRequest));
    }
}
