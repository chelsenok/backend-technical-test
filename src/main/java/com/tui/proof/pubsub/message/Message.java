package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.pubsub.Topic;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Parent class for all specific messages in pub/sub
 */
@Data
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Message {

    private final UUID uuid;
    private final Topic topic;

    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00")
    private LocalDateTime processedAt;
    private boolean read;
    private Status status;
    private String exception;

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
        if (obj instanceof Message) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
