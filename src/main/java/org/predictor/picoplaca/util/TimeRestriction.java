package org.predictor.picoplaca.util;

import java.time.LocalTime;

/**
 * Class that represents the restricted time ranges of pico y placa
 *
 * @author martin
 */
public enum TimeRestriction {
    MORNING_START(7, 0),
    MORNING_FINISH(9, 30),
    EVENING_START(16, 0),
    EVENING_FINISH(19, 30);

    private LocalTime time;
    private int hour;
    private int minute;

    TimeRestriction(int hour, int minute) {
        this.time = LocalTime.of(hour, minute);
    }

    public LocalTime getTime() {
        return time;
    }
}
