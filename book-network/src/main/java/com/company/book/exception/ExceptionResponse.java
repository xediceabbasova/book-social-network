package com.company.book.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ExceptionResponse(
        Integer businessErrorCode,
        String businessErrorDescription,
        String error,
        Map<String, String> validationErrors,
        Map<String, String> errors
) {
}
