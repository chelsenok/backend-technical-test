package com.tui.proof.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {

    private final List<String> messages;
}
