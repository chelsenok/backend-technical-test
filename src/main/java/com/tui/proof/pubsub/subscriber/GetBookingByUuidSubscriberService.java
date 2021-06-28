package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetBookingByUuidMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetBookingByUuidSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        GetBookingByUuidMessage getBookingByUuidMessage = (GetBookingByUuidMessage) message;
        log.info("getBooking with message: {}", message);
        Booking booking = bookingService.getBooking(getBookingByUuidMessage.getBookingUuid());
        getBookingByUuidMessage.setBooking(booking);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_BOOKING_BY_UUID);
    }
}
