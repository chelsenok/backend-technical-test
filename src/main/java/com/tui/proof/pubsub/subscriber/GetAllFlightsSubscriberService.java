package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllFlightsMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllFlightsSubscriberService extends SubscriberService {

    private final FlightService flightService;

    @Override
    protected void processMessage(Message message) {
        GetAllFlightsMessage getAllFlightsMessage = (GetAllFlightsMessage) message;
        log.info("getAllFlights with message: {}", message);
        List<Flight> allFlights = flightService.getAllFlights();
        getAllFlightsMessage.getFlights().addAll(allFlights);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_ALL_FLIGHTS);
    }
}
