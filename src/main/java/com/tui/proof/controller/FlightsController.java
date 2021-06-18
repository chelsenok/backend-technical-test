package com.tui.proof.controller;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightAvailability;
import com.tui.proof.model.FlightAvailabilityRequest;
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
@RequestMapping(path = "${v1API}/flights")
public class FlightsController {

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Flight> getFlight(@PathVariable UUID uuid) {
        return null;
    }

    @PostMapping("/availabilities")
    public ResponseEntity<List<FlightAvailability>> checkFlightAvailability(
            @RequestBody FlightAvailabilityRequest flightAvailabilityRequest) {
        return null;
    }
}
