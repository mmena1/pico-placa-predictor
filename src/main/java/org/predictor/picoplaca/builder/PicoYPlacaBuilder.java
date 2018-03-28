package org.predictor.picoplaca.builder;

import org.predictor.picoplaca.converter.DateTimeConverter;
import org.predictor.picoplaca.exception.ConvertionException;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.validator.Validator;
import org.predictor.picoplaca.validator.Validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Singleton that creates and validates a {@code PicoYPlaca} object
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
     * Then it is validated to ensure the {@code PicoYPlaca} object is correctly built.
     *
     * @param licensePlate The license plate to be validated
     * @param date         The date in format dd/MM/yyyy
     * @param time         The time in 24-hour format HH:mm
     * @return a validated and correctly built {@code PicoYPlaca} object
     * @throws ValidationException in case a validation did not pass
     */
    public PicoYPlaca build(String licensePlate, String date, String time) throws ValidationException {
        try {
            DateTimeConverter converter = DateTimeConverter.getINSTANCE();
            LocalDate localDate = converter.convertToDate(date);
            LocalTime localTime = converter.convertToTime(time);

            PicoYPlaca picoYPlaca = new PicoYPlaca(licensePlate, localDate, localTime);
            validate(picoYPlaca);

            return new PicoYPlaca(licensePlate, localDate, localTime);
        } catch (ConvertionException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Validates that a {@code PicoYPlaca} object is correctly built
     *
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails
     */
    private void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        List<Validator> validators = Validators.get();
        for (Validator validator : validators) {
            validator.validate(picoYPlaca);
        }
    }

    /**
     * Returns the only instance of this class to be used
     * @return The {@code PicoYPlacaBuilder} instance
     */
    public static PicoYPlacaBuilder getINSTANCE() {
        return INSTANCE;
    }
}
