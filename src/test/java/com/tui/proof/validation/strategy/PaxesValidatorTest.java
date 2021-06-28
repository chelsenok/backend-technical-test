package com.tui.proof.validation.strategy;

import com.tui.proof.model.Paxes;
import com.tui.proof.utils.RandomObjectFiller;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class PaxesValidatorTest {

    @Test
    public void testNullValidation() {
        List<String> errors = new ArrayList<>();
        PaxesValidator.getInstance().validate(null, errors);
        assertThat(errors, is(not(empty())));
    }

    @Test
    public void testFailedValidation() {
        List<String> errors = new ArrayList<>();
        PaxesValidator.getInstance().validate(new Paxes(), errors);
        assertThat(errors, is(not(empty())));
    }

    @Test
    public void testSuccessValidation() {

        List<String> errors = new ArrayList<>();
        PaxesValidator.getInstance().validate(RandomObjectFiller.createAndFill(Paxes.class), errors);
        assertThat(errors, is(empty()));
    }
}
