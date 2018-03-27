package org.predictor.picoplaca.converter;

import org.predictor.picoplaca.exception.ConvertionException;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class DateTimeConverter {

    private Properties properties = PropertiesLoader.getProperties();

    private DateTimeConverter(){}

    private static final DateTimeConverter INSTANCE = new DateTimeConverter();

    /**
     * Converts a {@code String} date in the format dd/MM/yyyy to a {@code LocalDate} object
     *
     * @param date The date to be converted
     * @return A successfully converted {@code LocalDate} object
     * @throws ConvertionException in case the conversion fails
     */
    public LocalDate convertDate(String date) throws ConvertionException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(properties.getProperty("format.date"));
            return LocalDate.parse(date, dateFormat);
        } catch (Exception e) {
            String message = MessageFormat.format(properties.getProperty("message.invalid.date"), properties.getProperty("format.date"));
            throw new ConvertionException(message);
        }

    }

    /**
     * Converts a {@code String} time in the format HH:mm to a {@code LocalTime} object
     *
     * @param time The time to be converted
     * @return A successfully converted {@code LocalTime} object
     * @throws ConvertionException in case the conversion fails
     */
    public LocalTime convertTime(String time) throws ConvertionException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(properties.getProperty("format.time"));
            return LocalTime.parse(time, dateFormat);
        } catch (Exception e) {
            String message = MessageFormat.format(properties.getProperty("message.invalid.time"), properties.getProperty("format.time"));
            throw new ConvertionException(message);
        }

    }

    public static DateTimeConverter getINSTANCE() {
        return INSTANCE;
    }
}
