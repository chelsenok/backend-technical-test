package com.tui.proof.validation.strategy;

import com.tui.proof.model.BookingRequest;

import java.util.List;

/**
 * BookingRequest validation strategy
 */
public class BookingValidator implements Validator<BookingRequest> {

    private BookingValidator() {
    }

    public static BookingValidator getInstance() {
        return BillPughSingleton.VALIDATOR_INSTANCE;
    }

    @Override
    public void validate(BookingRequest obj, List<String> errorCollector) {
        if (obj == null) {
            errorCollector.add("booking can not be null");
            return;
        }

        HolderValidator.getInstance().validate(obj.getHolder(), errorCollector);
    }

    /**
     * BillPughSingleton pattern
     */
    private static class BillPughSingleton {
        private static final BookingValidator VALIDATOR_INSTANCE = new BookingValidator();
    }
}
