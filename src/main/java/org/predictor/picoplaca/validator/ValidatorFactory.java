package org.predictor.picoplaca.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorFactory {
    private List<Validator> validators = new ArrayList<>();

    private ValidatorFactory(){}

    private static final ValidatorFactory INSTANCE = new ValidatorFactory();

    public List<Validator> getValidators() {
        if (validators.isEmpty()) {
            validators.add(new LicensePlateValidator());
            validators.add(new DayValidator());
            validators.add(new TimeValidator());
        }
        return validators;
    }

    public static ValidatorFactory getINSTANCE() {
        return INSTANCE;
    }
}
