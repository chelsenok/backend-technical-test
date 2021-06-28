package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.ConfirmBookingMessage;
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
public class ConfirmBookingSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        ConfirmBookingMessage confirmBookingMessage = (ConfirmBookingMessage) message;
        log.info("getBooking with message: {}", message);
        Booking booking = bookingService.getBooking(confirmBookingMessage.getBookingUuid());
        log.info("validateBooking with message: {}", message);
        bookingService.validateBooking(booking);
        log.info("assertBookingInCreatedStatus with message: {}", message);
        bookingService.assertBookingInCreatedStatus(booking);
        log.info("confirmBooking with message: {}", message);
        bookingService.confirmBooking(booking);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.CONFIRM_BOOKING);
    }
}
