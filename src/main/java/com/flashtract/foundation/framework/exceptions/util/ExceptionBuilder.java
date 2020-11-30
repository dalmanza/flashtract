package com.flashtract.foundation.framework.exceptions.util;

import com.flashtract.foundation.framework.exceptions.FlashtractBusinessException;
import com.flashtract.foundation.framework.exceptions.FlashtractSystemException;

/**
 * Exception builder artifact
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */

public final class ExceptionBuilder {

    private String message;
    private Throwable parentException;

    private ExceptionBuilder() {
    }

    public static ExceptionBuilder builder() {
        return new ExceptionBuilder();
    }

    public ExceptionBuilder withMessage(final String customMessage) {
        this.message = customMessage;
        return this;
    }

    public ExceptionBuilder withParentException(final Throwable providedParentException) {
        this.parentException = providedParentException;
        return this;
    }

    public FlashtractBusinessException buildBusinessException() {
        return new FlashtractBusinessException(message, parentException);
    }

    public FlashtractSystemException buildSystemException() {
        return new FlashtractSystemException(message, parentException);
    }

}
