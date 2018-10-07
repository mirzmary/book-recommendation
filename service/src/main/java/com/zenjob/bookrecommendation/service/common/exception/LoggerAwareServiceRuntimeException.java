package com.zenjob.bookrecommendation.service.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAwareServiceRuntimeException extends ServiceRuntimeException {

    private static final long serialVersionUID = 2262659855494070161L;
    protected final Logger LOGGER;

    public LoggerAwareServiceRuntimeException(final Object caller, final String message, final Object... args) {
        super(String.format(message, args));
        final String errorMessage = String.format(message, args);
        this.LOGGER = LoggerFactory.getLogger(caller.getClass());
        this.LOGGER.error(errorMessage);
    }

    public LoggerAwareServiceRuntimeException(final Object caller, final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
        final String errorMessage = String.format(message, args);
        this.LOGGER = LoggerFactory.getLogger(caller.getClass());
        this.LOGGER.error(errorMessage);
    }
}
