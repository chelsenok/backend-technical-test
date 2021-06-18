package com.tui.proof.service;

import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightsAvailabilityService {

    private final FlightService flightService;

    public List<FlightsAvailability> searchFlightsAvailabilities(FlightsAvailabilityRequest flightsAvailabilityRequest) {
        List<Flight> flights = flightService.searchFlights(flightsAvailabilityRequest);
        return null;
    }
}
