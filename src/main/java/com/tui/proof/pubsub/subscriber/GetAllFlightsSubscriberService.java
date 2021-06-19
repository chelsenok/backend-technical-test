package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllFlightsMessage;
import com.tui.proof.pubsub.message.Message;
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
