package com.tui.proof.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Holder {

    private String name;
    private String lastName;
    private String address;
    private String postalCode;
    private String country;
    private String email;
    private List<String> telephones;
}
