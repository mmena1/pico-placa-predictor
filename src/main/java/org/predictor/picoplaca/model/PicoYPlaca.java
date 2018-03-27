package org.predictor.picoplaca.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class representing a Pico y Placa object. It contains a license plate number, a date and a time.
 * All the fields are final so that they are all required when creating this object and cannot change afterwards.
 *
 * @author martin
 */
public class PicoYPlaca {

    private final String licensePlate;
    private final LocalDate date;
    private final LocalTime time;

    public PicoYPlaca(String licensePlate, LocalDate date, LocalTime time) {
        this.licensePlate = licensePlate;
        this.date = date;
        this.time = time;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
