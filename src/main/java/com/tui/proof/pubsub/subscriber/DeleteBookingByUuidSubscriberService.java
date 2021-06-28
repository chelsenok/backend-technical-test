package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteBookingByUuidMessage;
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
public class DeleteBookingByUuidSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        DeleteBookingByUuidMessage deleteBookingByUuidMessage = (DeleteBookingByUuidMessage) message;
        log.info("getBooking with message: {}", message);
        Booking booking = bookingService.getBooking(deleteBookingByUuidMessage.getBookingUuid());
        log.info("assertBookingInCreatedStatus with message: {}", message);
        bookingService.assertBookingInCreatedStatus(booking);
        log.info("deleteBooking with message: {}", message);
        bookingService.deleteBooking(booking);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.DELETE_BOOKING_BY_UUID);
    }
}
