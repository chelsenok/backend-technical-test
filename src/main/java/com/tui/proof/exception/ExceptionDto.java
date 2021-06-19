package com.tui.proof.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Custom ExceptionDto
 */
@Getter
@Builder
public class ExceptionDto {

    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00")
    private final LocalDateTime timestamp;
    private final int status;
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
        if (obj instanceof ExceptionDto) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
