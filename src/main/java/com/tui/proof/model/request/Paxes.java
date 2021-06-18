package com.tui.proof.model.request;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
public class Paxes {

    @PositiveOrZero
    private short infants;
    @PositiveOrZero
    private short children;
    @PositiveOrZero
    private short adults;
}
