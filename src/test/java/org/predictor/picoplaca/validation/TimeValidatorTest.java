package org.predictor.picoplaca.validation;

import org.junit.Assert;
import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeValidatorTest {

    @Test
    public void successTest() {
        try {
            TimeValidator timeValidator = new TimeValidator();
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4542", "26/03/2018", "08:00");
            timeValidator.validate(picoYPlaca);
        } catch (ValidationException | ConversionException e) {
            Assert.fail();
        }
    }

    @Test
    public void failTest() {
        try {
            TimeValidator timeValidator = new TimeValidator();
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4542", "26/03/2018", "11:00");
            timeValidator.validate(picoYPlaca);
            Assert.fail();
        } catch (ValidationException e) {
            String messsage = "The license number {0} does not have pico y placa on {1} at {2}.";
            assertThat(e.getMessage(), containsString(MessageFormat.format(messsage, "PTY-4542", "26/03/2018", "11:00")));
        } catch (ConversionException e) {
            Assert.fail();
        }
    }
}
