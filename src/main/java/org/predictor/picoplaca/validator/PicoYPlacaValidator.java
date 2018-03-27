package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.DayRestriction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Singleton for checking if a license plate is allowed to be on the road at a given date and time.
 *
 * @author martin
 */
public class PicoYPlacaValidator {

    /**
     * Private constructor so nobody can instantiate it
     */
    private PicoYPlacaValidator() {
    }

    /**
     * Creates just one instance of this object.
     */
    private static final PicoYPlacaValidator INSTANCE = new PicoYPlacaValidator();

    /**
     * Validates whether the provided license plate is allowed on the road ata given date and time
     *
     * @param licensePlate The license plate to validate
     * @param date         The date to validate the license plate against
     * @param time         The time to validate the license plate against
     * @return true if it is not allowed, false otherwise
     * @throws ValidationException in case the validation was not possible due to incorrect arguments which did not comply with the required format
     */
    public boolean validate(String licensePlate, String date, String time) throws ValidationException {

        PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build(licensePlate, date, time);
//        char lastDigit = picoYPlaca.getLicensePlate().charAt(licensePlate.length() - 1);
//        Integer lastDigitInt = Integer.parseInt(String.valueOf(lastDigit));

//        return isRestrictedDay(lastDigitInt, picoYPlaca.getDate()) && isRestrictedTime(picoYPlaca.getTime());
        return false;

    }

    /**
     * Checks if the last digit of a license plate is inside the restriction days.
     *
     * @param lastDigit The last digit to validate
     * @param date      The date to validate the last digit against
     * @return true if it is inside the restricted days, false otherwise (if its Saturday or Sunday).
     */
    private boolean isRestrictedDay(Integer lastDigit, LocalDate date) {
//        DayOfWeek dayOfWeek = date.getDayOfWeek();
//        switch (dayOfWeek) {
//
//            case MONDAY:
//                return validateRestrictionDay(DayRestriction.MONDAY, lastDigit);
//            case TUESDAY:
//                return validateRestrictionDay(DayRestriction.TUESDAY, lastDigit);
//            case WEDNESDAY:
//                return validateRestrictionDay(DayRestriction.WEDNESDAY, lastDigit);
//            case THURSDAY:
//                return validateRestrictionDay(DayRestriction.THURSDAY, lastDigit);
//            case FRIDAY:
//                return validateRestrictionDay(DayRestriction.FRIDAY, lastDigit);
//            default:
//                return false;
//        }
        return false;
    }

    /**
     * Validates if the last digit of a license plate number is contained in the array of the specified day.
     *
     * @param dayRestriction The day which contains the restricted last digits of license plate numbers
     * @param lastDigit      The last digit to check
     * @return true if it is contained, false otherwise
     */
    private boolean validateRestrictionDay(DayRestriction dayRestriction, Integer lastDigit) {
        return Arrays.stream(dayRestriction.getLastDigits()).anyMatch(digit -> digit.equals(lastDigit));
    }

    /**
     * Validates if the time provided is between the restricted time ranges.
     *
     * @param time The time to validate
     * @return true if the time is between the restricted time ranges, false otherwise
     */
    private boolean isRestrictedTime(LocalTime time) {
        // Start restriction times have to be -1 minute because its inclusive
        // End restriction times have to be +1 minute because its exclusive
//        LocalTime startMorning = TimeRestriction.MORNING_START.getTime().minusMinutes(1);
//        LocalTime finishMorning = TimeRestriction.MORNING_FINISH.getTime().plusMinutes(1);
//
//        LocalTime startEvening = TimeRestriction.EVENING_START.getTime().minusMinutes(1);
//        LocalTime finishEvening = TimeRestriction.EVENING_FINISH.getTime().plusMinutes(1);
//
//        return time.isBefore(finishMorning) && time.isAfter(startMorning) || time.isBefore(finishEvening) && time.isAfter(startEvening);
        return false;

    }

    public static PicoYPlacaValidator getINSTANCE() {
        return INSTANCE;
    }
}
