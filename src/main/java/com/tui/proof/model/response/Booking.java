package com.tui.proof.model.response;

import lombok.Data;

import java.util.List;

@Data
public class Booking {

    private Holder holder;
    private List<Flight> flights;
}
