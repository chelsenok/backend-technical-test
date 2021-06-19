package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteFlightFromBookingMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteFlightFromBookingSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        DeleteFlightFromBookingMessage deleteFlightFromBookingMessage = (DeleteFlightFromBookingMessage) message;
        bookingService.deleteFlightByUuid(deleteFlightFromBookingMessage.getBookingUuid(), deleteFlightFromBookingMessage.getFlightUuid());
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.DELETE_FLIGHT_FROM_BOOKING);
    }
}
