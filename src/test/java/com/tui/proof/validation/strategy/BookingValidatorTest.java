package com.tui.proof.validation.strategy;

import com.tui.proof.model.BookingRequest;
import com.tui.proof.utils.RandomObjectFiller;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class BookingValidatorTest {

    @Test
    public void testNullValidation() {
        List<String> errors = new ArrayList<>();
        BookingValidator.getInstance().validate(null, errors);
        assertThat(errors, is(not(empty())));
    }

    @Test
    public void testFailedValidation() {
        List<String> errors = new ArrayList<>();
        BookingValidator.getInstance().validate(new BookingRequest(), errors);
        assertThat(errors, is(not(empty())));
    }

    @Test
    public void testSuccessValidation() {

        List<String> errors = new ArrayList<>();
        BookingValidator.getInstance().validate(RandomObjectFiller.createAndFill(BookingRequest.class), errors);
        assertThat(errors, is(empty()));
    }
}
