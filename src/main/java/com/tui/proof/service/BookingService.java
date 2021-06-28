package com.tui.proof.service;

import com.tui.proof.converter.BookingRequestToBookingConverter;
import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.NotFoundException;
import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.model.BookingStatus;
import com.tui.proof.model.Flight;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Booking service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final PublisherService publisherService;
    private final BookingRepository bookingRepository;
    private final FlightsAvailabilityService flightsAvailabilityService;

    /**
     * Publish CREATE_BOOKING message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishCreateBooking(BookingRequest bookingRequest) {
        return publisherService.publish(Topic.CREATE_BOOKING, Collections.singletonMap("booking_request", bookingRequest));
    }

    /**
     * Publish GET_ALL_BOOKINGS message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishGetAllBookings() {
        return publisherService.publish(Topic.GET_ALL_BOOKINGS, Collections.emptyMap());
    }

    /**
     * Publish CONFIRM_BOOKING message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishConfirmBooking(UUID uuid) {
        return publisherService.publish(Topic.CONFIRM_BOOKING, Collections.singletonMap("uuid", uuid));
    }

    /**
     * Publish GET_BOOKING_BY_UUID message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishGetBooking(UUID uuid) {
        return publisherService.publish(Topic.GET_BOOKING_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    /**
     * Publish DELETE_BOOKING_BY_UUID message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishDeleteBooking(UUID uuid) {
        return publisherService.publish(Topic.DELETE_BOOKING_BY_UUID, Collections.singletonMap("uuid", uuid));
    }

    /**
     * Publish ADD_FLIGHT_TO_BOOKING message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishAddFlightByAvailabilityUuid(UUID uuid, UUID availabilityUuid) {
        return publisherService.publish(
                Topic.ADD_FLIGHT_TO_BOOKING,
                new HashMap<Object, Object>() {{
                    put("uuid", uuid);
                    put("availability_uuid", availabilityUuid);
                }}
        );
    }

    /**
     * Publish DELETE_FLIGHT_FROM_BOOKING message to pub/sub
     *
     * @return list of published messages
     */
    public List<PublishedMessage> publishDeleteFlightByUuid(UUID uuid, UUID flightUuid) {
        return publisherService.publish(
                Topic.DELETE_FLIGHT_FROM_BOOKING,
                new HashMap<Object, Object>() {{
                    put("uuid", uuid);
                    put("flight_uuid", flightUuid);
                }}
        );
    }

    /**
     * Validate booking by checking inner availabilities
     *
     * @param booking booking to validate
     */
    public void validateBookingForAvailabilities(Booking booking) {
        booking.getFlightAvailabilities()
                .forEach(flightsAvailabilityService::assertFlightAvailability);
    }

    /**
     * Confirm booking.
     *
     * @param booking booking to confirm
     */
    public void confirmBooking(Booking booking) {
        booking.setBookingStatus(BookingStatus.CONFIRMED);
    }

    /**
     * Create new booking by request params
     *
     * @param bookingRequest request params
     * @return created booking
     */
    public Booking createBooking(BookingRequest bookingRequest) {
        Booking booking = BookingRequestToBookingConverter.getInstance().convert(bookingRequest);
        booking = bookingRepository.save(booking);
        booking.setBookingStatus(BookingStatus.CREATED);
        return booking;
    }

    /**
     * Add flight availability to the booking
     *
     * @param booking      booking to update
     * @param availability availability to be added in booking
     */
    public void addFlightByAvailabilityUuid(Booking booking, FlightsAvailability availability) {
        booking.getFlightAvailabilities().add(availability);
    }

    /**
     * Delete flight from the booking
     *
     * @param booking booking to update
     * @param flight  flight to be deleted from booking
     * @throws ForbiddenException if there is no such flight in booking
     */
    public void deleteFlightByUuid(Booking booking, Flight flight) {
        Optional<FlightsAvailability> availabilityToDelete = booking.getFlightAvailabilities().stream()
                .filter(availability -> availability.getFlight().equals(flight))
                .findFirst();
        if (!availabilityToDelete.isPresent()) {
            throw new ForbiddenException(
                    MessageFormatter.format("There is no such flight {} in booking {}", flight.getUuid(), booking.getUuid()).getMessage()
            );
        }
        booking.getFlightAvailabilities().remove(availabilityToDelete.get());
    }

    /**
     * Get all bookings
     *
     * @return list of all bookings
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Find booking by identifier
     *
     * @return found booking
     * @throws NotFoundException if booking was not found by provided uuid
     */
    public Booking getBooking(UUID uuid) {
        return bookingRepository.findByUuid(uuid);
    }

    /**
     * Check does booking is in CREATED status
     *
     * @param booking booking to check
     * @throws ForbiddenException if booking is not in CREATED status
     */
    public void assertBookingInCreatedStatus(Booking booking) {
        if (booking.getBookingStatus() != BookingStatus.CREATED) {
            log.warn("Booking {} can not be deleted. Is not in CREATED status", booking.getUuid());
            throw new ForbiddenException(
                    MessageFormatter.format("Booking {} can not be deleted. Is not in CREATED status", booking.getUuid()).getMessage()
            );
        }
    }

    /**
     * Delete booking.
     *
     * @param booking booking to delete
     */
    public void deleteBooking(Booking booking) {
        booking.setBookingStatus(BookingStatus.DELETED);
    }
}
