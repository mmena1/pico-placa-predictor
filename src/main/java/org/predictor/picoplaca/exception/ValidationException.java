package org.predictor.picoplaca.exception;

/**
 * Custom class for exception handling
 *
 * @author martin
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
