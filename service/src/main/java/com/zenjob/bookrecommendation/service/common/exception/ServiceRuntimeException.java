package com.zenjob.bookrecommendation.service.common.exception;

public class ServiceRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 4128477601254431338L;

    public ServiceRuntimeException() {

    }

    public ServiceRuntimeException(final String message) {
        super(message);
    }

    public ServiceRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}