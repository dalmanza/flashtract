package com.flashtract.foundation.framework.exceptions;


/**
 * Exception definition
 *
 * @author blanclabs
 * @version 1.0
 * @since 2018-12-07
 */

public class FlashtractBusinessException
        extends FlashtractApplicationException {

    private static final long serialVersionUID = 1L;

    public FlashtractBusinessException(final String message) {
        super(message);
    }

    public FlashtractBusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
