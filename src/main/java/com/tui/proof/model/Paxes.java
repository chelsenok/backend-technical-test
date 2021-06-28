package com.tui.proof.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Paxes model
 */
@Data
public class Paxes {

    private int infants;
    private int children;
    private int adults;

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
        if (obj instanceof Paxes) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
