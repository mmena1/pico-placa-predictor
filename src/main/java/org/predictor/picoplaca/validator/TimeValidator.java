package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;
import org.predictor.picoplaca.util.TimeRestriction;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.Properties;

/**
 * Implementation of {@link Validator} to validate the time of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class TimeValidator implements Validator {

    private Properties properties = PropertiesLoader.getProperties();

    /**
     * Validates if the time provided in the {@link PicoYPlaca} object is between the restricted time ranges.
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails, meaning it does not have pico y placa
     */
    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        // Start restriction times have to be -1 minute because its inclusive
        // End restriction times have to be +1 minute because its exclusive
        LocalTime startMorning = TimeRestriction.MORNING_START.getTime().minusMinutes(1);
        LocalTime finishMorning = TimeRestriction.MORNING_FINISH.getTime().plusMinutes(1);

        LocalTime startEvening = TimeRestriction.EVENING_START.getTime().minusMinutes(1);
        LocalTime finishEvening = TimeRestriction.EVENING_FINISH.getTime().plusMinutes(1);

        if (!(picoYPlaca.getTime().isBefore(finishMorning) && picoYPlaca.getTime().isAfter(startMorning)
                || picoYPlaca.getTime().isBefore(finishEvening) && picoYPlaca.getTime().isAfter(startEvening))) {
            String message = MessageFormat.format(properties.getProperty("message.no.picoyplaca"), picoYPlaca.getLicensePlate(), picoYPlaca.getDate(), picoYPlaca.getTime());
            throw new ValidationException(message);
        }
    }
}
