package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.TimeRestriction;

import java.time.LocalTime;

public class TimeValidator implements Validator {

    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        LocalTime startMorning = TimeRestriction.MORNING_START.getTime().minusMinutes(1);
        LocalTime finishMorning = TimeRestriction.MORNING_FINISH.getTime().plusMinutes(1);

        LocalTime startEvening = TimeRestriction.EVENING_START.getTime().minusMinutes(1);
        LocalTime finishEvening = TimeRestriction.EVENING_FINISH.getTime().plusMinutes(1);

        if (!(picoYPlaca.getTime().isBefore(finishMorning) && picoYPlaca.getTime().isAfter(startMorning)
                || picoYPlaca.getTime().isBefore(finishEvening) && picoYPlaca.getTime().isAfter(startEvening))) {
            throw new ValidationException("Does not have pico y placa time");
        }
    }
}
