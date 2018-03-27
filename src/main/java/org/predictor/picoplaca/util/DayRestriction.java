package org.predictor.picoplaca.util;

import java.time.DayOfWeek;

/**
 * Class that represents the Pico y Placa restrictions in each day with the corresponding last digits of a license plate number.
 *
 * @author martin
 */
public enum DayRestriction {

    MONDAY(DayOfWeek.MONDAY, 1, 2),
    TUESDAY(DayOfWeek.TUESDAY, 3, 4),
    WEDNESDAY(DayOfWeek.WEDNESDAY, 5, 6),
    THURSDAY(DayOfWeek.THURSDAY, 7, 8),
    FRIDAY(DayOfWeek.FRIDAY, 9, 0);

    private Integer[] lastDigits;
    private DayOfWeek dayOfWeek;

    DayRestriction(DayOfWeek dayOfWeek, Integer... lastDigits) {
        this.dayOfWeek = dayOfWeek;
        this.lastDigits = lastDigits;
    }

    public Integer[] getLastDigits() {
        return lastDigits;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
