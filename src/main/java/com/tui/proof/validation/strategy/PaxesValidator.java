package com.tui.proof.validation.strategy;

import com.tui.proof.model.Paxes;

import java.util.List;

/**
 * Paxes validation strategy
 */
public class PaxesValidator implements Validator<Paxes> {

    private PaxesValidator() {
    }

    public static PaxesValidator getInstance() {
        return PaxesValidator.BillPughSingleton.PAXES_VALIDATOR;
    }

    @Override
    public void validate(Paxes obj, List<String> errorCollector) {
        if (obj == null) {
            errorCollector.add("paxes can not be null");
            return;
        }

        if (obj.getInfants() < 0) {
            errorCollector.add("count of infants can not be negative");
        }
        if (obj.getChildren() < 0) {
            errorCollector.add("count of children can not be negative");
        }
        if (obj.getAdults() < 0) {
            errorCollector.add("count of adults can not be negative");
        }
        if (obj.getInfants() == 0 && obj.getChildren() == 0 && obj.getAdults() == 0) {
            errorCollector.add("all counters are equals to zero");
        }
    }

    /**
     * BillPughSingleton pattern
     */
    private static class BillPughSingleton {
        private static final PaxesValidator PAXES_VALIDATOR = new PaxesValidator();
    }
}
