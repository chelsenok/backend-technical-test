package com.tui.proof.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Custom BadRequestException
 */
@Getter
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {

    private final List<String> messages;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BadRequestException) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
