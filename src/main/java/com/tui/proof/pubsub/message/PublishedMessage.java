package com.tui.proof.pubsub.message;

import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * Published message model
 */
@Getter
@RequiredArgsConstructor
public class PublishedMessage {

    private final UUID uuid;
    private final Topic topic;
    private final String channel;

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
        if (obj instanceof PublishedMessage) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
        return false;
    }
}
