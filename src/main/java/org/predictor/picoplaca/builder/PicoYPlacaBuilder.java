package org.predictor.picoplaca.builder;

import org.predictor.picoplaca.conversion.DateTimeConverter;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
     * @param messages     The list to append messages in case something goes wrong.
     * @return a validated and correctly built {@code PicoYPlaca} object or null in case something fails
     */
    public PicoYPlaca build(String licensePlate, String date, String time, List<String> messages) {
        DateTimeConverter converter = DateTimeConverter.getINSTANCE();
        LocalDate localDate = null;
        List<String> localMessages = new ArrayList<>();
        if (!licensePlate.matches(PropertiesLoader.getProperties().getProperty("regex.license.plate"))) {
            localMessages.add(PropertiesLoader.getProperties().getProperty("message.invalid.license.plate"));
        }
        try {
            localDate = converter.convertToDate(date);
        } catch (ConversionException e) {
            localMessages.add(e.getMessage());
        }
        LocalTime localTime = null;
        try {
            localTime = converter.convertToTime(time);
        } catch (ConversionException e) {
            localMessages.add(e.getMessage());
        }
        if (localMessages.isEmpty()) {
            return new PicoYPlaca(licensePlate, localDate, localTime);

        }
        messages.addAll(localMessages);
        return null;
    }

    /**
     * Returns the only instance of this class to be used
     *
     * @return The {@code PicoYPlacaBuilder} instance
     */
    public static PicoYPlacaBuilder getINSTANCE() {
        return INSTANCE;
    }
}
