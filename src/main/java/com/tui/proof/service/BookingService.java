package com.tui.proof.service;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.pubsub.PublisherService;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.utils.Stub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final PublisherService publisherService;

    public List<PublishedMessage> publishCreateBooking(BookingRequest bookingRequest) {
        return publisherService.publish(Topic.CREATE_BOOKING, Collections.singletonMap("booking_request", bookingRequest));
    }

    @Stub
    public Booking createBooking(BookingRequest bookingRequest) {
        return new Booking();
    }
}
