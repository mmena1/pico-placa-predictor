package org.predictor.picoplaca.builder;

import org.predictor.picoplaca.converter.DateTimeConverter;
import org.predictor.picoplaca.exception.ConvertionException;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;
import org.predictor.picoplaca.validator.Validator;
import org.predictor.picoplaca.validator.ValidatorFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

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

    private Properties properties = PropertiesLoader.getProperties();

    /**
     * Builds a {@code PicoYPlaca} object from 3 {@code String} objects: license plate, date and time.
     * They are validated, so that the {@code PicoYPlaca} object is correctly built.
     *
     * @param licensePlate The license plate to be validated
     * @param date         The date in format dd/MM/yyyy
     * @param time         The time in 24-hour format HH:mm
     * @return a validated and correctly built {@code PicoYPlaca} object
     * @throws ValidationException in case a validation did not pass
     */
    public PicoYPlaca build(String licensePlate, String date, String time) throws ValidationException {
//        boolean valid = validateLicensePlate(licensePlate);
//        if (!valid) {
//            throw new ValidationException(properties.getProperty("message.invalid.license.plate"));
//        }
        try {
            DateTimeConverter converter = DateTimeConverter.getINSTANCE();
            LocalDate localDate = converter.convertDate(date);
            LocalTime localTime = converter.convertTime(time);

            PicoYPlaca picoYPlaca = new PicoYPlaca(licensePlate, localDate, localTime);

            List<Validator> validators = ValidatorFactory.getINSTANCE().getValidators();
            for (Validator validator : validators) {
                validator.validate(picoYPlaca);
            }
            return new PicoYPlaca(licensePlate, localDate, localTime);
        } catch (ConvertionException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Validates a license plate number against a regular expression
     *
     * @param licensePlate The license plate number to be validated
     * @return true if valid, false otherwise
     */
    private boolean validateLicensePlate(String licensePlate) {
        return licensePlate.matches(properties.getProperty("regex.license.plate"));
    }

    /**
     * Returns the only instance of this class to be used
     * @return The {@code PicoYPlacaBuilder} instance
     */
    public static PicoYPlacaBuilder getINSTANCE() {
        return INSTANCE;
    }
}
