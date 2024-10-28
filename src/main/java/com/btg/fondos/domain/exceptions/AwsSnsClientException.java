package com.btg.fondos.domain.exceptions;


/**
 * This exception used in AwsService
 */
public class AwsSnsClientException extends RuntimeException {

    /**
     * Aws Client Exception with error message and throwable
     *
     * @param errorMessage error message
     * @param throwable    error
     */
    public AwsSnsClientException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}