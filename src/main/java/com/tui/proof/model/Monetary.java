package com.tui.proof.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * Monetary model
 */
@Data
public class Monetary {

    @Schema(example = "150.35")
    private BigDecimal amount;
    private Currency currency;

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
        if (obj instanceof Monetary) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
