package com.tui.proof.controller.api;

import com.tui.proof.exception.ExceptionDto;
import com.tui.proof.model.FlightsAvailability;
import com.tui.proof.model.FlightsAvailabilityRequest;
import com.tui.proof.pubsub.message.PublishedMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "availability-api (flight)", description = "REST API for working with flights")
public interface FlightApi {

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (flight)",
            summary = "Get all flights",
            description = "Method to get all flights"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description = "Request was published successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PublishedMessage.class))
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
                    responseCode = "500",
                    description = "Internal server exception",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            )
    })
    ResponseEntity<List<PublishedMessage>> getAllFlights();

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (flight)",
            summary = "Get flight by identity",
            description = "Method to get flight by identity"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description = "Request was published successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PublishedMessage.class))
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
                    responseCode = "500",
                    description = "Internal server exception",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            )
    })
    ResponseEntity<List<PublishedMessage>> getFlight(UUID uuid);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (flight)",
            summary = "Search for flight availabilities",
            description = "Method to search flight availabilities"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of flight availabilities",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = FlightsAvailability.class))
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
                    responseCode = "500",
                    description = "Internal server exception",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            )
    })
    ResponseEntity<List<FlightsAvailability>> searchFlightsAvailabilities(FlightsAvailabilityRequest flightsAvailabilityRequest);
}
