package org.predictor.picoplaca.builder;

import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Singleton class for building output messages from the messages.properties file.
 *
 * @author martin
 */
public class PicoYPlacaMessageBuilder {

    private Properties properties = PropertiesLoader.getProperties();

    /**
     * Creates exactly one instance of this class
     */
    private static final PicoYPlacaMessageBuilder INSTANCE = new PicoYPlacaMessageBuilder();

    /**
     * Private constructor so nobody can instantiate it
     */
    private PicoYPlacaMessageBuilder() {
    }

    /**
     * Builds a message for a license plate number which has no pico y placa in a given date and time
     *
     * @param picoYPlaca The object containing the license plate number, date and time
     * @return The output message
     */
    public String noPicoYPlaca(PicoYPlaca picoYPlaca) {
        return buildPicoYPlacaMessage(picoYPlaca, properties.getProperty("message.no.picoyplaca"));
    }

    /**
     * Builds a message for a license plate number which has pico y placa in a given date and time
     *
     * @param picoYPlaca The object containing the license plate number, date and time
     * @return The output message
     */
    public String hasPicoYPlaca(PicoYPlaca picoYPlaca) {
        return buildPicoYPlacaMessage(picoYPlaca, properties.getProperty("message.has.picoyplaca"));
    }

    /**
     * Builds the message for both methods {@code noPicoYPlaca} and {@code hasPicoYPlaca} as they have the same format
     *
     * @param picoYPlaca The object containing the license plate number, date and time
     * @param message    The message from the {@code messages.properties} file
     * @return The output message
     */
    private String buildPicoYPlacaMessage(PicoYPlaca picoYPlaca, String message) {
        LocalDate date = picoYPlaca.getDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(properties.getProperty("format.date"));
        LocalTime time = picoYPlaca.getTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(properties.getProperty("format.time"));
        return MessageFormat.format(message, picoYPlaca.getLicensePlate(), date.format(dateFormatter), time.format(timeFormatter));
    }

    /**
     * Builds a message for an invalid license plate number
     *
     * @return The output message
     */
    public String invalidLicensePlate() {
        return properties.getProperty("message.invalid.license.plate");
    }

    /**
     * Builds a message for an invalid date
     *
     * @return The output message
     */
    public String invalidDate() {
        return MessageFormat.format(properties.getProperty("message.invalid.date"), properties.getProperty("format.date"));
    }

    /**
     * Builds a message for an invalid time
     *
     * @return The output message
     */
    public String invalidTime() {
        return MessageFormat.format(properties.getProperty("message.invalid.time"), properties.getProperty("format.time"));
    }

    public static PicoYPlacaMessageBuilder getINSTANCE() {
        return INSTANCE;
    }
}
