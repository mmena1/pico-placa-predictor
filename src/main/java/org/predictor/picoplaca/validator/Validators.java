package org.predictor.picoplaca.validator;

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
        validators.add(new LicensePlateValidator());
        validators.add(new DayValidator());
        validators.add(new TimeValidator());
    }

    public static List<Validator> get() {
        return validators;
    }

}
