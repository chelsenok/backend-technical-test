package com.tui.proof.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Holder {

    @Schema(example = "John")
    private String name;
    @Schema(example = "Smith")
    private String lastName;
    @Schema(example = "1935 Willow Greene Drive")
    private String address;
    @Schema(example = "20170")
    private String postalCode;
    @Schema(example = "US")
    private String country;
    @Schema(example = "john.smith@gmail.com")
    private String email;
    @ArraySchema(schema = @Schema(example = "334-229-9944"))
    private List<String> telephones;

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
        if (obj instanceof Holder) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
