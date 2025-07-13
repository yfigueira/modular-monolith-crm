package org.example.activitymodule.activity.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ExceptionResponse(
        String message,
        Set<String> validationErrors,
        LocalDateTime timestamp
) {
}
