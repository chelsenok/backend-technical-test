package com.tui.proof.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Booking {

    private UUID uuid;
    private Holder holder;
    private List<UUID> flights;
}
