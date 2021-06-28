package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Flight model
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flight {

    private UUID uuid;
    @Schema(example = "Evergreen")
    private String company;
    @Schema(example = "BA2490")
    private String flightNumber;
    @Schema(example = "2021-06-19")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
    @Schema(example = "09:15")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private Monetary price;

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
        if (obj instanceof Flight) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
