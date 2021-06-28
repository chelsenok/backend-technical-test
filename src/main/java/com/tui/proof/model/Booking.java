package com.tui.proof.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Booking model
 */
@Data
@Builder
public class Booking {

    private UUID uuid;
    private Holder holder;
    private BookingStatus bookingStatus;
    private List<FlightsAvailability> flightAvailabilities = new ArrayList<>();

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
        if (obj instanceof Booking) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
