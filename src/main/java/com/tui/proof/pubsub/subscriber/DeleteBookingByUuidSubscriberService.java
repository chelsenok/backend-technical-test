package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteBookingByUuidMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteBookingByUuidSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        DeleteBookingByUuidMessage deleteBookingByUuidMessage = (DeleteBookingByUuidMessage) message;
        bookingService.deleteBooking(deleteBookingByUuidMessage.getBookingUuid());
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.DELETE_BOOKING_BY_UUID);
    }
}
