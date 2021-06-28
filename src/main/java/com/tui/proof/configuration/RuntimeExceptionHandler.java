package com.tui.proof.configuration;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.exception.ExceptionDto;
import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.NotFoundException;
import com.tui.proof.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Custom runtime exception handler.
 */
@Slf4j
@RestControllerAdvice
public class RuntimeExceptionHandler {

    /**
     * Handler for UNAUTHORIZED
     *
     * @param exception UnauthorizedException
     * @return ExceptionDto
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDto> unauthorizedException(UnauthorizedException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handler for FORBIDDEN
     *
     * @param exception ForbiddenException
     * @return ExceptionDto
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionDto> forbiddenException(ForbiddenException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    /**
     * Handler for BAD_REQUEST
     *
     * @param exception BadRequestException
     * @return ExceptionDto
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> badRequestException(BadRequestException exception) {
        return buildResponseEntity(exception.getMessages(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for NOT_FOUND
     *
     * @param exception NotFoundException
     * @return ExceptionDto
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFoundException(NotFoundException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handler for INTERNAL_SERVER_ERROR
     *
     * @param exception Exception
     * @return ExceptionDto
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Error response entity builder.
     *
     * @param messages   list of message strings
     * @param httpStatus status
     * @return built response entity
     */
    private ResponseEntity<ExceptionDto> buildResponseEntity(List<String> messages, HttpStatus httpStatus) {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .messages(messages)
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
        log.error("Exception was caught by RuntimeExceptionHandler: {}", exceptionDto);
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDto);
    }
}
