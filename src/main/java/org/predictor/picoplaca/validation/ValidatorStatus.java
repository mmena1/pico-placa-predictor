package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.util.Status;

public class ValidatorStatus {
    private final Status status;
    private final String message;

    public ValidatorStatus(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
