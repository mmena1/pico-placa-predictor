package org.predictor.picoplaca.validation;


import org.predictor.picoplaca.model.PicoYPlaca;

/**
 * Interface which implements all validation objects
 *
 * @author martin
 */
public interface Validator {


    /**
     * Validates the {@link PicoYPlaca} object received is correctly build
     *
     * @param picoYPlaca The object to be validated
     */
    void validate(PicoYPlaca picoYPlaca);

    ValidatorStatus getStatus();
}
