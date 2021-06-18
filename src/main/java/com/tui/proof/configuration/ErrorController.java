package com.tui.proof.configuration;

import com.tui.proof.exception.ExceptionDto;
import com.tui.proof.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDto> unauthorizedException(UnauthorizedException exception) {
        return buildResponseEntity(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidException(MethodArgumentNotValidException throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error occurs with request parameters: ");
        throwable.getBindingResult().getFieldErrors()
                .forEach(t -> sb.append(String.format("'%s' - %s ,", t.getField(), t.getDefaultMessage())));
        String s = sb.toString();
        String message = s.substring(0, s.length() - 2).concat(".");
        return buildResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception) {
        return buildResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionDto> buildResponseEntity(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(new ExceptionDto(message));
    }
}
