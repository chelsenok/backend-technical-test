package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteFlightFromBookingMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import com.tui.proof.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteFlightFromBookingSubscriberService extends SubscriberService {

    private final FlightService flightService;
    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        DeleteFlightFromBookingMessage deleteFlightFromBookingMessage = (DeleteFlightFromBookingMessage) message;
        log.info("getFlight with message: {}", message);
        Flight flight = flightService.getFlight(deleteFlightFromBookingMessage.getFlightUuid());
        log.info("getBooking with message: {}", message);
        Booking booking = bookingService.getBooking(deleteFlightFromBookingMessage.getBookingUuid());
        log.info("assertBookingInCreatedStatus with message: {}", message);
        bookingService.assertBookingInCreatedStatus(booking);
        log.info("deleteFlightByUuid with message: {}", message);
        bookingService.deleteFlightByUuid(booking, flight);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.DELETE_FLIGHT_FROM_BOOKING);
    }
}
