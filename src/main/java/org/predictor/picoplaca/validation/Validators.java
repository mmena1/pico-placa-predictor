package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class that produces a list of validators
 *
 * @author martin
 */
public final class Validators {

    private Validators() {
    }

    private static List<Validator> validators;

    static {
        validators = new ArrayList<>();
        validators.add(new DayValidator());
        validators.add(new TimeValidator());
    }

    /**
     * Executes all validations against a {@link PicoYPlaca} object to determine if the specified license plate number has pico y placa at the given date and time
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException in case the validation fails
     */
    public static void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        for (Validator validator : validators) {
            validator.validate(picoYPlaca);
        }
    }

}
