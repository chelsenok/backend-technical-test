package com.tui.proof.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ExceptionDto {

    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00")
    private final LocalDateTime timestamp;
    private final int status;
    private final List<String> messages;
}
