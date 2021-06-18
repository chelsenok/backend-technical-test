package com.tui.proof.validation.strategy;

import com.tui.proof.model.FlightAvailabilityRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FlightAvailabilityValidator implements Validator<FlightAvailabilityRequest> {

    private FlightAvailabilityValidator() {
    }

    public static FlightAvailabilityValidator getInstance() {
        return BillPughSingleton.FLIGHT_AVAILABILITY_VALIDATOR_INSTANCE;
    }

    @Override
    public void validate(FlightAvailabilityRequest obj, List<String> errorCollector) {
        if (obj == null) {
            errorCollector.add("flight availability can not be null");
            return;
        }

        if (StringUtils.isBlank(obj.getAirportOrigin())) {
            errorCollector.add("airport of origin can not be blank");
        }
        if (StringUtils.isBlank(obj.getAirportDestination())) {
            errorCollector.add("airport of destination can not be blank");
        }
        if (obj.getDateFrom() == null) {
            errorCollector.add("from date can not be null");
        }
        if (obj.getDateTo() == null) {
            errorCollector.add("to date can not be null");
        }
        PaxesValidator.getInstance().validate(obj.getPaxes(), errorCollector);
    }

    private static class BillPughSingleton {
        private static final FlightAvailabilityValidator FLIGHT_AVAILABILITY_VALIDATOR_INSTANCE = new FlightAvailabilityValidator();
    }
}
