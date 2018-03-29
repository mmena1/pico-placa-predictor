package org.predictor.picoplaca.validation;

/**
 * Abstrac validator class that implements the {@link Validator} interface and provides a {@link ValidatorStatus} to all its children
 */
public abstract class AbstractValidator implements Validator {
    protected ValidatorStatus status;

    public ValidatorStatus getStatus() {
        return status;
    }

}
