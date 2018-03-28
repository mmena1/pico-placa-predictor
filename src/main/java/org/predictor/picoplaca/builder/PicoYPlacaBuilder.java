package org.predictor.picoplaca.builder;

import org.predictor.picoplaca.conversion.DateTimeConverter;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Singleton class that creates a {@code PicoYPlaca} object
 *
 * @author martin
 */
public class PicoYPlacaBuilder {


    /**
     * Private constructor so that nobody can instantiate it
     */
    private PicoYPlacaBuilder() {
    }

    /**
     * Creates just one instance of this class
     */
    private static final PicoYPlacaBuilder INSTANCE = new PicoYPlacaBuilder();

    /**
     * Builds a {@code PicoYPlaca} object from 3 {@code String} objects: license plate, date and time.
     *
     * @param licensePlate The license plate to be validated
     * @param date         The date in format dd/MM/yyyy
     * @param time         The time in 24-hour format HH:mm
     * @return a validated and correctly built {@code PicoYPlaca} object
     * @throws ConversionException in case the conversion fails
     */
    public PicoYPlaca build(String licensePlate, String date, String time) {
            DateTimeConverter converter = DateTimeConverter.getINSTANCE();
        LocalDate localDate = null;
        licensePlate.matches(PropertiesLoader.getProperties().getProperty("regex.license.plate"));
        try {
            localDate = converter.convertToDate(date);
        } catch (ConversionException e) {
            e.printStackTrace();
        }
        LocalTime localTime = null;
        try {
            localTime = converter.convertToTime(time);
        } catch (ConversionException e) {
            e.printStackTrace();
        }

        return new PicoYPlaca(licensePlate, localDate, localTime);
    }

    /**
     * Returns the only instance of this class to be used
     * @return The {@code PicoYPlacaBuilder} instance
     */
    public static PicoYPlacaBuilder getINSTANCE() {
        return INSTANCE;
    }
}
