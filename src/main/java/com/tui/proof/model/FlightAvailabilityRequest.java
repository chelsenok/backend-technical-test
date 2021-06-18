package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FlightAvailabilityRequest {

    private String airportOrigin;
    private String airportDestination;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateFrom;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateTo;
    private Paxes paxes;
}
