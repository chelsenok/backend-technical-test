package com.tui.proof.controller;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.pubsub.message.PublishedMessage;
import com.tui.proof.service.BookingService;
import com.tui.proof.service.SecurityService;
import com.tui.proof.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "${api.v1}/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final SecurityService securityService;
    private final ValidationService validationService;

    @Value("${api.v1.messages}")
    private String messagesLocation;

    @PostMapping
    public ResponseEntity<List<PublishedMessage>> createBooking(@RequestBody BookingRequest bookingRequest) {
        validationService.validate(bookingRequest);
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(bookingService.publishCreateBooking(bookingRequest));
    }

    @GetMapping
    public ResponseEntity<List<PublishedMessage>> getAllBookings() {
        securityService.assertCurrentUserAdmin();
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, messagesLocation)
                .body(bookingService.publishGetAllBookings());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Booking> getBooking(@PathVariable UUID uuid) {
        return null;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID uuid) {
        return null;
    }

    @PostMapping("/{uuid}/confirm")
    public ResponseEntity<Void> confirmBooking(@PathVariable UUID uuid) {
        return null;
    }

    @PutMapping("/{uuid}/flight")
    public ResponseEntity<Void> addFlightByAvailabilityUuid(@PathVariable UUID uuid,
                                                            @RequestParam("availability_uuid") UUID availabilityUuid) {
        return null;
    }

    @DeleteMapping("/{uuid}/flight/{flightUuid}")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID uuid,
                                             @PathVariable UUID flightUuid) {
        return null;
    }
}
