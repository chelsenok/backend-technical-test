package com.tui.proof.service;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.utils.Stub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final PublisherService publisherService;
    private final FlightsAvailabilityService flightsAvailabilityService;

    public List<PublishedMessage> publishCreateBooking(BookingRequest bookingRequest) {
        return publisherService.publish(Topic.CREATE_BOOKING, Collections.singletonMap("booking_request", bookingRequest));
    }

    public List<PublishedMessage> publishGetAllBookings() {
        return publisherService.publish(Topic.GET_ALL_BOOKINGS, Collections.emptyMap());
    }

    public List<PublishedMessage> publishConfirmBooking(UUID uuid) {
        return publisherService.publish(Topic.CONFIRM_BOOKING, Collections.singletonMap("uuid", uuid));
    }

    public List<PublishedMessage> publishGetBooking(UUID uuid) {
        return publisherService.publish(Topic.GET_BOOKING_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    public List<PublishedMessage> publishDeleteBooking(UUID uuid) {
        return publisherService.publish(Topic.DELETE_BOOKING_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    public List<PublishedMessage> publishAddFlightByAvailabilityUuid(UUID uuid, UUID availabilityUuid) {
        return publisherService.publish(
                Topic.ADD_FLIGHT_TO_BOOKING,
                new HashMap<Object, Object>() {{
                    put("uuid", uuid);
                    put("availability_uuid", availabilityUuid);
                }}
        );
    }

    public List<PublishedMessage> publishDeleteFlightByUuid(UUID uuid, UUID flightUuid) {
        return publisherService.publish(
                Topic.ADD_FLIGHT_TO_BOOKING,
                new HashMap<Object, Object>() {{
                    put("uuid", uuid);
                    put("flight_uuid", flightUuid);
                }}
        );
    }

    public void validateBooking(UUID uuid) {
        Booking booking = getBooking(uuid);
        booking.getFlightAvailabilities().stream()
                .map(FlightsAvailability::getAvailabilityUuid)
                .forEach(flightsAvailabilityService::assertFlightAvailability);
    }

    @Stub
    public void confirmBooking(UUID uuid) {
        log.warn("Stubbing for confirmBooking");
    }

    @Stub
    public Booking createBooking(BookingRequest bookingRequest) {
        log.warn("Stubbing for createBooking");
        return new Booking();
    }

    @Stub
    public List<Booking> getAllBookings() {
        log.warn("Stubbing for getAllBookings");
        return new ArrayList<>();
    }

    @Stub
    public Booking getBooking(UUID uuid) {
        log.warn("Stubbing for getBooking");
        return new Booking();
    }

    @Stub
    public void deleteBooking(UUID uuid) {
        log.warn("Stubbing for deleteBooking");
    }

    @Stub
    public void addFlightByAvailabilityUuid(UUID bookingUuid, UUID availabilityUuid) {
        log.warn("Stubbing for addFlightByAvailabilityUuid");
    }

    @Stub
    public void deleteFlightByUuid(UUID bookingUuid, UUID flightUuid) {
        log.warn("Stubbing for deleteFlightByUuid");
    }
}
