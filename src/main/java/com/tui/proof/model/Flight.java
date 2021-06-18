package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Flight {

    private UUID uuid;
    private UUID availabilityUuid;
    private String company;
    private String flightNumber;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private BigDecimal price;
}
