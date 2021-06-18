package com.tui.proof.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Holder {

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String postalCode;
    @NotNull
    private String country;
    @NotNull
    private String email;
    @NotEmpty
    private List<String> telephones;
}
