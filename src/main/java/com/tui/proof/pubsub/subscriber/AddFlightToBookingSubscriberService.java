package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.AddFlightToBookingMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import com.tui.proof.service.FlightsAvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddFlightToBookingSubscriberService extends SubscriberService {

    private final BookingService bookingService;
    private final FlightsAvailabilityService flightsAvailabilityService;

    @Override
    protected void processMessage(Message message) {
        AddFlightToBookingMessage addFlightToBookingMessage = (AddFlightToBookingMessage) message;
        log.info("getFlightAvailability with message: {}", message);
        FlightsAvailability availability = flightsAvailabilityService.getFlightAvailability(addFlightToBookingMessage.getAvailabilityUuid());
        log.info("assertFlightAvailability with message: {}", message);
        flightsAvailabilityService.assertFlightAvailability(availability);
        log.info("getBooking with message: {}", message);
        Booking booking = bookingService.getBooking(addFlightToBookingMessage.getBookingUuid());
        log.info("addFlightByAvailabilityUuid with message: {}", message);
        bookingService.addFlightByAvailabilityUuid(booking, availability);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.ADD_FLIGHT_TO_BOOKING);
    }
}
