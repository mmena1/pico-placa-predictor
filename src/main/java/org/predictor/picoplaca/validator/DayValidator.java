package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.DayRestriction;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Properties;

/**
 * Implementation of {@link Validator} to validate the day of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class DayValidator implements Validator {

    private Properties properties = PropertiesLoader.getProperties();

    /**
     * Validates if the last digit of the license plate number in a {@link PicoYPlaca} object is inside the restriction days.
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails. Meaning it does not have pico y placa.
     */
    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        DayOfWeek dayOfWeek = picoYPlaca.getDate().getDayOfWeek();
        char lastChar = picoYPlaca.getLicensePlate().charAt(picoYPlaca.getLicensePlate().length() - 1);
        Integer lastDigit = Integer.parseInt(String.valueOf(lastChar));
        switch (dayOfWeek) {

            case MONDAY:
                validateRestrictionDay(DayRestriction.MONDAY, lastDigit, picoYPlaca);
                break;
            case TUESDAY:
                validateRestrictionDay(DayRestriction.TUESDAY, lastDigit, picoYPlaca);
                break;
            case WEDNESDAY:
                validateRestrictionDay(DayRestriction.WEDNESDAY, lastDigit, picoYPlaca);
                break;
            case THURSDAY:
                validateRestrictionDay(DayRestriction.THURSDAY, lastDigit, picoYPlaca);
                break;
            case FRIDAY:
                validateRestrictionDay(DayRestriction.FRIDAY, lastDigit, picoYPlaca);
                break;
            default:
                String message = MessageFormat.format(properties.getProperty("message.no.picoyplaca"), picoYPlaca.getLicensePlate(), picoYPlaca.getDate(), picoYPlaca.getTime());
                throw new ValidationException(message);
        }
    }

    /**
     * Validates if the last digit of a license plate number is contained inside the array of restriced numbers of the specified day.
     *
     * @param dayRestriction The day which contains the restricted last digits of license plate numbers
     * @param lastDigit      The last digit to check
     * @param picoYPlaca     The {@link PicoYPlaca} object for building the message in case the validation fails
     * @throws ValidationException if validation fails, which means it dos not have pico y placa
     */
    private void validateRestrictionDay(DayRestriction dayRestriction, Integer lastDigit, PicoYPlaca picoYPlaca) throws ValidationException {
        boolean restricted = Arrays.stream(dayRestriction.getLastDigits()).anyMatch(digit -> digit.equals(lastDigit));
        if (!restricted) {
            String message = MessageFormat.format(properties.getProperty("message.no.picoyplaca"), picoYPlaca.getLicensePlate(), picoYPlaca.getDate(), picoYPlaca.getTime());
            throw new ValidationException(message);
        }
    }
}
