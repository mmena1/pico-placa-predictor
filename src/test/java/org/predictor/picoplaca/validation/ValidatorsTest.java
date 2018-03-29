package org.predictor.picoplaca.validation;

import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.text.MessageFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ValidatorsTest {

    private void assertPicoYPlacaFormattedMessage(String licensePlate, String date, String time, boolean hasPicoYPlaca) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time);
            assertThat(picoYPlaca, notNullValue());
            Validators.validate(picoYPlaca);
            if (!hasPicoYPlaca) {
                fail();
            }
        } catch (ValidationException e) {
            if (hasPicoYPlaca) {
                fail();
            }
            String messsage = "The license number {0} does not have pico y placa on {1} at {2}.";
            assertThat(e.getMessage(), containsString(MessageFormat.format(messsage, licensePlate, date, time)));
        } catch (ConversionException e) {
            fail();
        }
    }

    @Test
    public void hasPicoYPlacaTest() {

        String message = "The license number {0} has pico y placa on {1} at {2}.";

        assertPicoYPlacaFormattedMessage("PTY-4542", "26/03/2018", "08:00", true);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "19:30", true);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "09:30", true);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "07:00", true);

    }

    @Test
    public void doesNotHavePicoYPlacaTest() {

        String message = "The license number {0} does not have pico y placa on {1} at {2}.";

        assertPicoYPlacaFormattedMessage("PTY-4543", "26/03/2018", "08:00", false);
        assertPicoYPlacaFormattedMessage("PTY-4541", "27/03/2018", "19:30", false);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "09:31", false);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "06:59", false);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "11:00", false);

    }

}
