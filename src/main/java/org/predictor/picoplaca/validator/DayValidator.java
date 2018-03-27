package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.DayRestriction;

import java.time.DayOfWeek;
import java.util.Arrays;

public class DayValidator implements Validator {

    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        DayOfWeek dayOfWeek = picoYPlaca.getDate().getDayOfWeek();
        char lastChar = picoYPlaca.getLicensePlate().charAt(picoYPlaca.getLicensePlate().length() - 1);
        Integer lastDigit = Integer.parseInt(String.valueOf(lastChar));
        switch (dayOfWeek) {

            case MONDAY:
                validateRestrictionDay(DayRestriction.MONDAY, lastDigit);
                break;
            case TUESDAY:
                validateRestrictionDay(DayRestriction.TUESDAY, lastDigit);
                break;
            case WEDNESDAY:
                validateRestrictionDay(DayRestriction.WEDNESDAY, lastDigit);
                break;
            case THURSDAY:
                validateRestrictionDay(DayRestriction.THURSDAY, lastDigit);
                break;
            case FRIDAY:
                validateRestrictionDay(DayRestriction.FRIDAY, lastDigit);
                break;
            default:
                throw new ValidationException("Does not have pico y placa");
        }
    }

    /**
     * Validates if the last digit of a license plate number is contained in the array of the specified day.
     *
     * @param dayRestriction The day which contains the restricted last digits of license plate numbers
     * @param lastDigit      The last digit to check
     * @return true if it is contained, false otherwise
     */
    private void validateRestrictionDay(DayRestriction dayRestriction, Integer lastDigit) throws ValidationException {
        boolean restricted = Arrays.stream(dayRestriction.getLastDigits()).anyMatch(digit -> digit.equals(lastDigit));
        if (!restricted) {
            throw new ValidationException("Does not have pico y placa day");
        }
    }
}
