package com.tui.proof.controller.api;

import com.tui.proof.exception.ExceptionDto;
import com.tui.proof.model.BookingRequest;
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

@Tag(name = "availability-api (booking)", description = "REST API for working with bookings")
public interface BookingApi {

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Creation of booking",
            description = "Method creates new booking"
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
    ResponseEntity<List<PublishedMessage>> createBooking(BookingRequest bookingRequest);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Get all bookings",
            description = "Method for administrator to get all bookings"
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
                    responseCode = "403",
                    description = "User does not have admin rights",
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
    ResponseEntity<List<PublishedMessage>> getAllBookings();

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Get booking by identity",
            description = "Method to get booking by identity"
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
    ResponseEntity<List<PublishedMessage>> getBooking(UUID uuid);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Delete booking by identity",
            description = "Method to delete booking by identity"
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
    ResponseEntity<List<PublishedMessage>> deleteBooking(UUID uuid);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Confirm booking by identity",
            description = "Method to confirm booking by identity"
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
                    responseCode = "403",
                    description = "Flight availability is expired",
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
    ResponseEntity<List<PublishedMessage>> confirmBooking(UUID uuid);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Add flight to booking by availability",
            description = "Method to add flight to booking by availability"
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
                    responseCode = "403",
                    description = "Flight availability is expired",
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
    ResponseEntity<List<PublishedMessage>> addFlightByAvailabilityUuid(UUID uuid, UUID availabilityUuid);

    @Operation(
            security = @SecurityRequirement(name = "availability-api-auth"),
            tags = "availability-api (booking)",
            summary = "Delete flight to booking by flight identity",
            description = "Method to delete flight to booking by flight identity"
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
    ResponseEntity<List<PublishedMessage>> deleteFlight(UUID uuid, UUID flightUuid);
}
