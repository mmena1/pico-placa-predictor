package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.Status;

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
     * Validates whether a license plate number in a {@link PicoYPlaca} object is allowed to circulate on the road at a given date and time
     *
     * @param picoYPlaca The object to be validated
     * @return the list of messages
     */
    public static List<String> validate(PicoYPlaca picoYPlaca) {
        List<String> validatorStatuses = new ArrayList<>();
        for (Validator validator : validators) {
            validator.validate(picoYPlaca);
            if (validator.getStatus().getStatus().equals(Status.FAIL)) {
                validatorStatuses.add(validator.getStatus().getMessage());
            }
        }
        if (validatorStatuses.isEmpty()) {
            validatorStatuses.add(PicoYPlacaMessageBuilder.getINSTANCE().hasPicoYPlaca(picoYPlaca));
        }
        return validatorStatuses;
    }

}
