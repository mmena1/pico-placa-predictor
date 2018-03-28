package org.predictor.picoplaca.conversion;

import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton class that converts strings to {@code LocalDate} and {@code LocalTime} objects.
 *
 * @author martin
 */
public class DateTimeConverter {

    private DateTimeConverter() {
    }

    private static final DateTimeConverter INSTANCE = new DateTimeConverter();

    /**
     * Converts a {@code String} date in the format dd/MM/yyyy to a {@code LocalDate} object
     *
     * @param date The date to be converted
     * @return A successfully converted {@code LocalDate} object
     * @throws ConversionException in case the conversion fails
     */
    public LocalDate convertToDate(String date) throws ConversionException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(PropertiesLoader.getProperties().getProperty("format.date"));
            return LocalDate.parse(date, dateFormat);
        } catch (Exception e) {
            throw new ConversionException(PicoYPlacaMessageBuilder.getINSTANCE().invalidDate());
        }

    }

    /**
     * Converts a {@code String} time in the format HH:mm to a {@code LocalTime} object
     *
     * @param time The time to be converted
     * @return A successfully converted {@code LocalTime} object
     * @throws ConversionException in case the conversion fails
     */
    public LocalTime convertToTime(String time) throws ConversionException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(PropertiesLoader.getProperties().getProperty("format.time"));
            return LocalTime.parse(time, dateFormat);
        } catch (Exception e) {
            throw new ConversionException(PicoYPlacaMessageBuilder.getINSTANCE().invalidTime());
        }

    }

    public static DateTimeConverter getINSTANCE() {
        return INSTANCE;
    }
}
