package com.flashtract.crosscutting.domain;

import com.flashtract.crosscutting.constants.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Canonical domain entity
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {

    private final ErrorType type;
    private final String description;

}
