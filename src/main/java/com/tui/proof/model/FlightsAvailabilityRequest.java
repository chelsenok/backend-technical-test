package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FlightsAvailabilityRequest {

    private String airportOrigin;
    private String airportDestination;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateFrom;
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
