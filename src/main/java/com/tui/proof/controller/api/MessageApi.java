package com.tui.proof.controller.api;

import com.tui.proof.exception.ExceptionDto;
import com.tui.proof.pubsub.message.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "availability-api (message)", description = "REST API for working with messages")
public interface MessageApi {

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (message)",
            summary = "Get message by identity",
            description = "Method to get message by identity"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found message",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Message.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authorized",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Message is not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server exception",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            )
    })
    ResponseEntity<Message> getMessage(UUID uuid);
}
