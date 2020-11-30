package com.flashtract.foundation.framework.exceptions.util;

import com.flashtract.crosscutting.constants.ErrorType;
import com.flashtract.crosscutting.domain.ErrorResponseDTO;
import com.flashtract.foundation.framework.exceptions.FlashtractApplicationException;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Builder artifact for simpliying spring response model
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */

public final class ErrorResponseEntityBuilder {

    private static final String SYSTEM_ERROR_MESSAGE = "An error has occurred. Please try again.";

    private HttpStatus status;
    private FlashtractApplicationException exception;

    private ErrorResponseEntityBuilder() {
    }

    public static ErrorResponseEntityBuilder builder() {
        return new ErrorResponseEntityBuilder();
    }

    public ErrorResponseEntityBuilder withStatus(final HttpStatus providedStatus) {
        this.status = providedStatus;
        return this;
    }

    public ErrorResponseEntityBuilder withException(
            final FlashtractApplicationException providedException) {
        this.exception = providedException;
        return this;
    }

    public ResponseEntity<Object> build() {

        final ErrorResponseDTO response = ErrorResponseDTO.builder()
                .description(
                        exception.isBusinessException() ? exception.getMessage()
                                : SYSTEM_ERROR_MESSAGE)
                .type(exception.isBusinessException() ? ErrorType.BUSINESS_ERROR
                        : ErrorType.SYSTEM_ERROR)
                .build();
        response.toString();
        return ResponseEntity
                .status(Objects.nonNull(status) ? status
                        : resolveStatus(exception.isBusinessException()))
                .body(response);
    }

    private HttpStatus resolveStatus(final boolean isBusinessException) {
        return isBusinessException ? HttpStatus.BAD_REQUEST
                : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
