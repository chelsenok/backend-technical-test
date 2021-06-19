package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Message {

    private final UUID uuid;
    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00")
    private LocalDateTime processedAt;
    private boolean read;
}
