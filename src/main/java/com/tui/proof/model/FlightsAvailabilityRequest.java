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

/**
 * FlightsAvailabilityRequest model
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FlightsAvailabilityRequest {

    @Schema(example = "LGA")
    private String airportOrigin;
    @Schema(example = "JFK")
    private String airportDestination;
    @Schema(example = "2021-06-19")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateFrom;
    @Schema(example = "2021-06-21")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateTo;
    private Paxes paxes;

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
        if (obj instanceof FlightsAvailabilityRequest) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
