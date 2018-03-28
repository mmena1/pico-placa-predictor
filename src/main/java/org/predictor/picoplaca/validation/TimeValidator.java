package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.Status;
import org.predictor.picoplaca.util.TimeRestriction;

import java.time.LocalTime;

/**
 * Implementation of {@link Validator} to validate the time of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class TimeValidator extends AbstractValidator {

    /**
     * Validates if the time provided in the {@link PicoYPlaca} object is between the restricted time ranges.
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails, meaning it does not have pico y placa
     */
    @Override
    public void validate(PicoYPlaca picoYPlaca) {
        // Start restriction times have to be -1 minute because its inclusive
        // End restriction times have to be +1 minute because its exclusive
        LocalTime startMorning = TimeRestriction.MORNING_START.getTime().minusMinutes(1);
        LocalTime finishMorning = TimeRestriction.MORNING_FINISH.getTime().plusMinutes(1);

        LocalTime startEvening = TimeRestriction.EVENING_START.getTime().minusMinutes(1);
        LocalTime finishEvening = TimeRestriction.EVENING_FINISH.getTime().plusMinutes(1);

        if (picoYPlaca.getTime().isBefore(finishMorning) && picoYPlaca.getTime().isAfter(startMorning)
                || picoYPlaca.getTime().isBefore(finishEvening) && picoYPlaca.getTime().isAfter(startEvening)) {
            status = new ValidatorStatus(Status.SUCCESS, "");
        } else {
            status = new ValidatorStatus(Status.FAIL, PicoYPlacaMessageBuilder.getINSTANCE().noPicoYPlaca(picoYPlaca));
        }
    }
}
