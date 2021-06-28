package com.tui.proof.converter;

import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class BookingRequestToBookingConverter implements Converter<BookingRequest, Booking> {

    private BookingRequestToBookingConverter() {
    }

    public static BookingRequestToBookingConverter getInstance() {
        return BookingRequestToBookingConverter.BillPughSingleton.CONVERTER_INSTANCE;
    }

    @NonNull
    @Override
    public Booking convert(BookingRequest bookingRequest) {
        return Booking.builder()
                .holder(bookingRequest.getHolder())
                .build();
    }

    /**
     * BillPughSingleton pattern
     */
    private static class BillPughSingleton {
        private static final BookingRequestToBookingConverter CONVERTER_INSTANCE = new BookingRequestToBookingConverter();
    }
}
