package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.DayRestriction;

import java.time.DayOfWeek;
import java.util.Arrays;

/**
 * Implements the {@link Validator} interface to validate the day of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class DayValidator implements Validator {

    /**
     * Validates if the last digit of the license plate number in a {@link PicoYPlaca} object is inside the restriction days.
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException in case the validation fails
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
                throw new ValidationException(PicoYPlacaMessageBuilder.getINSTANCE().noPicoYPlaca(picoYPlaca));
        }
    }

    /**
     * Validates if the last digit of a license plate number is contained inside the array of restriced numbers of the specified day.
     *
     * @param dayRestriction The day which contains the restricted last digits of license plate numbers
     * @param lastDigit      The last digit to check
     * @param picoYPlaca     The {@link PicoYPlaca} object for building the message in case the validation fails
     * @throws ValidationException in case the validation fails
     */
    private void validateRestrictionDay(DayRestriction dayRestriction, Integer lastDigit, PicoYPlaca picoYPlaca) throws ValidationException {
        boolean restricted = Arrays.stream(dayRestriction.getLastDigits()).anyMatch(digit -> digit.equals(lastDigit));
        if (!restricted) {
            throw new ValidationException(PicoYPlacaMessageBuilder.getINSTANCE().noPicoYPlaca(picoYPlaca));
        }
    }
}
