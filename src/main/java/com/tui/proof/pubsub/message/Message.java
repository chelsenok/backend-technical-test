package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tui.proof.pubsub.Topic;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Message {

    private final UUID uuid;
    private final Topic topic;

    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00")
    private LocalDateTime processedAt;
    private boolean read;
}
