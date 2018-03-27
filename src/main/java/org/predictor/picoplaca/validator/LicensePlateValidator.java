package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.util.Properties;

public class LicensePlateValidator implements Validator {

    private Properties properties = PropertiesLoader.getProperties();

    @Override
    public void validate(PicoYPlaca picoYPlaca) throws ValidationException {
        boolean correctLicensePlate = picoYPlaca.getLicensePlate().matches(properties.getProperty("regex.license.plate"));
        if (!correctLicensePlate) {
            throw new ValidationException(properties.getProperty("message.invalid.license.plate"));
        }
    }
}
