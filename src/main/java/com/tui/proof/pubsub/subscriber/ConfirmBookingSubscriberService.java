package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.ConfirmBookingMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfirmBookingSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        ConfirmBookingMessage confirmBookingMessage = (ConfirmBookingMessage) message;
        bookingService.validateBooking(confirmBookingMessage.getBookingUuid());
        bookingService.confirmBooking(confirmBookingMessage.getBookingUuid());
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.CONFIRM_BOOKING);
    }
}
