package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FlightAvailabilityRequest {

    @NotNull
    private String airportOrigin;
    @NotNull
    private String airportDestination;
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateFrom;
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateTo;
    @NotNull
    private Paxes paxes;
}
