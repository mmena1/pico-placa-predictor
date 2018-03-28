package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.util.Properties;

/**
 * Implementation of {@link Validator} to validate the license plate number of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class LicensePlateValidator implements Validator {

    private Properties properties = PropertiesLoader.getProperties();

    /**
     * Validates the license plate number is correctly formatted
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails.
     */
    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        boolean correctLicensePlate = picoYPlaca.getLicensePlate().matches(properties.getProperty("regex.license.plate"));
        if (!correctLicensePlate) {
            throw new ValidationException(properties.getProperty("message.invalid.license.plate"));
        }
    }
}
