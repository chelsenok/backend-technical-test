package com.tui.proof.controller;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightAvailabilityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "${v1API}/flights")
public class FlightsController {

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Flight> getFlight(@PathVariable UUID uuid) {
    }

    @PostMapping("/check_availability")
    public ResponseEntity<Flight> checkFlightAvailability(@Valid @RequestBody FlightAvailabilityRequest flightAvailabilityRequest) {
    }
}
