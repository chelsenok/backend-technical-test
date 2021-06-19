package com.tui.proof.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Custom NotFoundException
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

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
        if (obj instanceof NotFoundException) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
