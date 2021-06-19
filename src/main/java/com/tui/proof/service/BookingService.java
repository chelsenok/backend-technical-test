package com.tui.proof.service;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.utils.Stub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final PublisherService publisherService;

    public List<PublishedMessage> publishCreateBooking(BookingRequest bookingRequest) {
        return publisherService.publish(Topic.CREATE_BOOKING, Collections.singletonMap("booking_request", bookingRequest));
    }

    public List<PublishedMessage> publishGetAllBookings() {
        return publisherService.publish(Topic.GET_ALL_BOOKINGS, Collections.emptyMap());
    }

    @Stub
    public Booking createBooking(BookingRequest bookingRequest) {
        return new Booking();
    }

    @Stub
    public List<Booking> getAllBookings() {
        return new ArrayList<>();
    }
}
