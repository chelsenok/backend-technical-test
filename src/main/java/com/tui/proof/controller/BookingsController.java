package com.tui.proof.controller;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.service.SecurityService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "${v1API}/bookings")
@RequiredArgsConstructor
public class BookingsController {

    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        securityService.assertCurrentUserAdmin();
        return null;
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
