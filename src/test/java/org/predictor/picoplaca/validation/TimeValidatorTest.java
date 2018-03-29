package org.predictor.picoplaca.validation;

import org.junit.Assert;
import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.text.MessageFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeValidatorTest {

    private void assertTest(String time, boolean success) {
        String date = "26/03/2018";
        String licensePlate = "PTY-4542";
        try {
            TimeValidator timeValidator = new TimeValidator();
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build(licensePlate, date, time);
            timeValidator.validate(picoYPlaca);
            if (!success) {
                Assert.fail();
            }
        } catch (ConversionException e) {
            Assert.fail();
        } catch (ValidationException e) {
            if (success) {
                Assert.fail();
            }
            String messsage = "The license number {0} does not have pico y placa on {1} at {2}.";
            assertThat(e.getMessage(), containsString(MessageFormat.format(messsage, licensePlate, date, time)));
        }
    }

    @Test
    public void successTest() {
        assertTest("07:00", true);
        assertTest("09:30", true);
        assertTest("16:00", true);
        assertTest("19:30", true);
        assertTest("17:00", true);
    }

    @Test
    public void failTest() {
        assertTest("11:00", false);
        assertTest("06:59", false);
        assertTest("09:31", false);
        assertTest("15:59", false);
        assertTest("19:31", false);
    }
}
