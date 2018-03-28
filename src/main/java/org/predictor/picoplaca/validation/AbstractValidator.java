package org.predictor.picoplaca.validation;

public abstract class AbstractValidator implements Validator {
    protected ValidatorStatus status;

    public ValidatorStatus getStatus() {
        return status;
    }

}
