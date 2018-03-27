package org.predictor.picoplaca.validator;


import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

public interface Validator {
    void validate(PicoYPlaca picoYPlaca) throws ValidationException;
}
