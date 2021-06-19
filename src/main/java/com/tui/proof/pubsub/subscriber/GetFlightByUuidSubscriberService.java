package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetFlightByUuidMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFlightByUuidSubscriberService extends SubscriberService {

    private final FlightService flightService;

    @Override
    protected void processMessage(Message message) {
        GetFlightByUuidMessage getFlightByUuidMessage = (GetFlightByUuidMessage) message;
        Flight flight = flightService.getFlight(getFlightByUuidMessage.getFlightUuid());
        getFlightByUuidMessage.setFlight(flight);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_FLIGHT_BY_UUID);
    }
}
