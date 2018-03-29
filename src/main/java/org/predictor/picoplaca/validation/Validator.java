package org.predictor.picoplaca.validation;


import org.predictor.picoplaca.exception.ValidationException;
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
     * @throws ValidationException in case the validation fails
     */
    void validate(PicoYPlaca picoYPlaca) throws ValidationException;

}
