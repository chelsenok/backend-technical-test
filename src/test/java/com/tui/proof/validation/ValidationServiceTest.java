package com.tui.proof.validation;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.model.Holder;
import com.tui.proof.model.Paxes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    private static final ValidationService VALIDATION_SERVICE = new ValidationService();

    @Test
    public void testUnsupportedValidation() {
        assertThrows(UnsupportedOperationException.class, () -> VALIDATION_SERVICE.validate(Booking.builder().build()));
    }

    @Test
    public void testPaxesValidation() {
        assertThrows(BadRequestException.class, () -> VALIDATION_SERVICE.validate(new Paxes()));
    }

    @Test
    public void testHolderValidation() {
        assertThrows(BadRequestException.class, () -> VALIDATION_SERVICE.validate(new Holder()));
    }

    @Test
    public void testBookingRequestValidation() {
        assertThrows(BadRequestException.class, () -> VALIDATION_SERVICE.validate(new BookingRequest()));
    }

    @Test
    public void testFlightsAvailabilityRequestValidation() {
        assertThrows(BadRequestException.class, () -> VALIDATION_SERVICE.validate(new FlightsAvailabilityRequest()));
    }
}
