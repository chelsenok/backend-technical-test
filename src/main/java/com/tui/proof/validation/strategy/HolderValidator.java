package com.tui.proof.validation.strategy;

import com.tui.proof.model.Holder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class HolderValidator implements Validator<Holder> {

    private HolderValidator() {
    }

    public static HolderValidator getInstance() {
        return HolderValidator.BillPughSingleton.HOLDER_VALIDATOR;
    }

    @Override
    public void validate(Holder obj, List<String> errorCollector) {
        if (StringUtils.isBlank(obj.getName())) {
            errorCollector.add("name can not be blank");
        }
        if (StringUtils.isBlank(obj.getLastName())) {
            errorCollector.add("last name can not be blank");
        }
        if (StringUtils.isBlank(obj.getAddress())) {
            errorCollector.add("address can not be blank");
        }
        if (StringUtils.isBlank(obj.getPostalCode())) {
            errorCollector.add("postal code can not be blank");
        }
        if (StringUtils.isBlank(obj.getCountry())) {
            errorCollector.add("country can not be blank");
        }
        if (StringUtils.isBlank(obj.getEmail())) {
            errorCollector.add("email can not be blank");
        }
        if (CollectionUtils.isEmpty(obj.getTelephones())) {
            errorCollector.add("list of telephones can not be empty");
        }
    }

    private static class BillPughSingleton {
        private static final HolderValidator HOLDER_VALIDATOR = new HolderValidator();
    }
}
