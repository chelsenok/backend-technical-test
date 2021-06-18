package com.tui.proof.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${v1API}/bookings")
public class BookingsController {

    @PostMapping
    public ResponseEntity createBooking() {
    }

    @PutMapping("/add_flight")
    public ResponseEntity addFlight() {
    }
}
