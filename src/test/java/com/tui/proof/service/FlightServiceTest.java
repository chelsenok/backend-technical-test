package com.tui.proof.service;

import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void testGetAllFlights() {
        flightService.publishGetAllFlights();
        verify(publisherService, times(1)).publish(eq(Topic.GET_ALL_FLIGHTS), any());
    }

    @Test
    public void testGetFlight() {
        flightService.publishGetFlight(UUID.randomUUID());
        verify(publisherService, times(1)).publish(eq(Topic.GET_FLIGHT_BY_UUID), any());
    }
}
