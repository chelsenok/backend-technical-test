package com.tui.proof.validation.strategy;

import java.util.List;

public interface Validator<T extends Object> {

    void validate(T obj, List<String> errorCollector);
}
