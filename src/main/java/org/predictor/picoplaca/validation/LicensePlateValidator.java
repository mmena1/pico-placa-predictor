package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.PropertiesLoader;
import org.predictor.picoplaca.util.Status;

/**
 * Implementation of {@link Validator} to validate the license plate number of a {@link PicoYPlaca} object.
 *
 * @author martin
 */
public class LicensePlateValidator extends AbstractValidator {

    /**
     * Validates the license plate number is correctly formatted
     * @param picoYPlaca The object to be validated
     * @throws ValidationException if validation fails.
     */
    @Override
    public void validate(PicoYPlaca picoYPlaca) {
        boolean correctLicensePlate = picoYPlaca.getLicensePlate().matches(PropertiesLoader.getProperties().getProperty("regex.license.plate"));
        if (!correctLicensePlate) {
            status = new ValidatorStatus(Status.FAIL, PicoYPlacaMessageBuilder.getINSTANCE().invalidLicensePlate());
        } else {
            status = new ValidatorStatus(Status.SUCCESS, "");
        }
    }
}
