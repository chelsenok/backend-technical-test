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

@Slf4j
@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDto> unauthorizedException(UnauthorizedException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionDto> forbiddenException(ForbiddenException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> badRequestException(BadRequestException exception) {
        return buildResponseEntity(exception.getMessages(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFoundException(NotFoundException exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception) {
        return buildResponseEntity(Collections.singletonList(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
