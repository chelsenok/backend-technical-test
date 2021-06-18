package com.tui.proof.controller;

import com.tui.proof.model.request.FlightAvailabilityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${v1API}/flights")
public class FlightsController {

    @PostMapping("/check_availability")
    public ResponseEntity checkFlightAvailability(@Valid @RequestBody FlightAvailabilityRequest flightAvailabilityRequest) {
    }
}
