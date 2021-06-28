package com.tui.proof.validation.strategy;

import java.util.List;

/**
 * Validator interface for using as Strategy pattern.
 *
 * @param <T> type of object to be validated
 */
public interface Validator<T> {

    void validate(T obj, List<String> errorCollector);
}
