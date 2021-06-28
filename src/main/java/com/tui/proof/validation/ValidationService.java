package com.tui.proof.validation;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.model.Holder;
import com.tui.proof.model.Paxes;
import com.tui.proof.validation.strategy.BookingValidator;
import com.tui.proof.validation.strategy.FlightsAvailabilityValidator;
import com.tui.proof.validation.strategy.HolderValidator;
import com.tui.proof.validation.strategy.PaxesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation service.
 */
@Slf4j
@Service
public class ValidationService {

    /**
     * Validate object.
     *
     * @param obj object to validate
     */
    public void validate(Object obj) {
        log.info("Validating obj: {}", obj);
        List<String> errorCollector = new ArrayList<>();

        if (obj instanceof Paxes) {
            PaxesValidator.getInstance().validate((Paxes) obj, errorCollector);
        } else if (obj instanceof Holder) {
            HolderValidator.getInstance().validate((Holder) obj, errorCollector);
        } else if (obj instanceof BookingRequest) {
            BookingValidator.getInstance().validate((BookingRequest) obj, errorCollector);
        } else if (obj instanceof FlightsAvailabilityRequest) {
            FlightsAvailabilityValidator.getInstance().validate((FlightsAvailabilityRequest) obj, errorCollector);
        } else {
            throw new UnsupportedOperationException("Passed object is not supported for validation");
        }

        if (!errorCollector.isEmpty()) {
            throw new BadRequestException(errorCollector);
        }
    }
}
