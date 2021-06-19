package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllFlightsMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllFlightsSubscriberService extends SubscriberService {

    private final FlightService flightService;

    @Override
    protected void processMessage(Message message) {
        GetAllFlightsMessage getAllFlightsMessage = (GetAllFlightsMessage) message;
        List<Flight> allFlights = flightService.getAllFlights();
        getAllFlightsMessage.getFlights().addAll(allFlights);
        super.onMessage(getAllFlightsMessage);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_ALL_FLIGHTS);
    }
}
