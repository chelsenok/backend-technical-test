package com.tui.proof.service.event.subscriber;

import com.tui.proof.service.event.Topic;
import com.tui.proof.service.event.message.GetAllFlightsMessage;
import com.tui.proof.service.event.message.Message;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetAllFlightsSubscriberService extends SubscriberService {

    @Override
    public void onMessage(Message message) {
        GetAllFlightsMessage getAllFlightsMessage = (GetAllFlightsMessage) message;
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_ALL_FLIGHTS);
    }
}
