package com.tui.proof.validation;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.model.FlightAvailabilityRequest;
import com.tui.proof.model.Holder;
import com.tui.proof.model.Paxes;
import com.tui.proof.validation.strategy.BookingValidator;
import com.tui.proof.validation.strategy.FlightAvailabilityValidator;
import com.tui.proof.validation.strategy.HolderValidator;
import com.tui.proof.validation.strategy.PaxesValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    public void validate(Object obj) {
        List<String> errorCollector = new ArrayList<>();

        if (obj instanceof Paxes) {
            PaxesValidator.getInstance().validate((Paxes) obj, errorCollector);
        } else if (obj instanceof Holder) {
            HolderValidator.getInstance().validate((Holder) obj, errorCollector);
        } else if (obj instanceof BookingRequest) {
            BookingValidator.getInstance().validate((BookingRequest) obj, errorCollector);
        } else if (obj instanceof FlightAvailabilityRequest) {
            FlightAvailabilityValidator.getInstance().validate((FlightAvailabilityRequest) obj, errorCollector);
        } else {
            throw new UnsupportedOperationException("Passed object is not supported for validation");
        }

        if (!errorCollector.isEmpty()) {
            throw new BadRequestException(errorCollector);
        }
    }
}
