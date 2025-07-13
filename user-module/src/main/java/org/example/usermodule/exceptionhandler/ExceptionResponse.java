package org.example.usermodule.exceptionhandler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String message,
        LocalDateTime timestamp
) {
}
