package com.flashtract.foundation.framework.exceptions;

/**
 * Exception definition
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */

public class FlashtractApplicationException extends Exception {

    private static final long serialVersionUID = 1L;

    public FlashtractApplicationException(final String message) {
        super(message);
    }

    public FlashtractApplicationException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    public boolean isBusinessException() {
        return this instanceof FlashtractApplicationException;
    }

}
