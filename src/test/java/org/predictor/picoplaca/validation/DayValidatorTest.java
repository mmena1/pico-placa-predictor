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

public class DayValidatorTest {

    private void assertTest(String licensePlate, String date, boolean success) {
        String time = "08:00";
        try {
            DayValidator dayValidator = new DayValidator();
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build(licensePlate, date, time);
            dayValidator.validate(picoYPlaca);
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
        assertTest("PTY-4540", "23/03/2018", true);
        assertTest("PTY-4541", "26/03/2018", true);
        assertTest("PTY-4542", "26/03/2018", true);
        assertTest("PTY-4543", "27/03/2018", true);
        assertTest("PTY-4544", "27/03/2018", true);
        assertTest("PTY-4545", "28/03/2018", true);
        assertTest("PTY-4546", "28/03/2018", true);
        assertTest("PTY-4547", "29/03/2018", true);
        assertTest("PTY-4548", "29/03/2018", true);
        assertTest("PTY-4549", "23/03/2018", true);
    }

    @Test
    public void failTest() {
        assertTest("PTY-4542", "27/03/2018", false);
        assertTest("PTY-4542", "31/03/2018", false);
        assertTest("PTY-4542", "25/03/2018", false);
    }
}
