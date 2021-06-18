package com.tui.proof.validation.strategy;

import com.tui.proof.model.BookingRequest;

import java.util.List;

public class BookingValidator implements Validator<BookingRequest> {

    private BookingValidator() {
    }

    public static BookingValidator getInstance() {
        return BillPughSingleton.BOOKING_VALIDATOR_INSTANCE;
    }

    @Override
    public void validate(BookingRequest obj, List<String> errorCollector) {
        if (obj == null) {
            errorCollector.add("booking can not be null");
            return;
        }

        HolderValidator.getInstance().validate(obj.getHolder(), errorCollector);
    }

    private static class BillPughSingleton {
        private static final BookingValidator BOOKING_VALIDATOR_INSTANCE = new BookingValidator();
    }
}
