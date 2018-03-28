package org.predictor.picoplaca.validation;

import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.text.MessageFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class PicoYPlacaValidatorTest {

    private void assertPicoYPlacaFormattedMessage(String licensePlate, String date, String time, String message) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time);
            List<String> outputMessages = Validators.validate(picoYPlaca);
            assertThat(outputMessages.size(), is(1));
            String output = MessageFormat.format(message, licensePlate, date, time);
            assertThat(outputMessages.get(0), containsString(output));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void hasPicoYPlacaTest() {

        String message = "The license {0} has pico y placa on {1} at {2}.";

        assertPicoYPlacaFormattedMessage("PTY-4542", "26/03/2018", "08:00", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "19:30", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "09:30", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "07:00", message);

    }

    @Test
    public void doesNotHavePicoYPlacaTest() {

        String message = "The license {0} does not have pico y placa on {1} at {2}.";

        assertPicoYPlacaFormattedMessage("PTY-4543", "26/03/2018", "08:00", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "27/03/2018", "19:30", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "09:31", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "06:59", message);
        assertPicoYPlacaFormattedMessage("PTY-4541", "26/03/2018", "11:00", message);

    }

    private void assertPicoYPlacaMessage(String licensePlate, String date, String time, String message) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time);
            List<String> outputMessages = Validators.validate(picoYPlaca);
            assertThat(outputMessages.size(), is(1));
            assertThat(outputMessages.get(0), containsString(message));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void invalidLicensePlateTest() {

        String message = "Invalid license plate. Please make sure you provide a license plate with the following format: ABC-1234 or ABC-123";

        assertPicoYPlacaMessage("asdf", "26/03/2018", "08:00", message);
        assertPicoYPlacaMessage("PTY-4541-1", "26/03/2018", "08:00", message);
        assertPicoYPlacaMessage("PTY4541", "26/03/2018", "08:00", message);
    }

    @Test
    public void invalidDateTest() {

        String message = "Invalid date. Please make sure you provide a date in format dd/MM/yyyy";

        assertPicoYPlacaMessage("PTY-4541", "26032018", "08:00", message);
        assertPicoYPlacaMessage("PTY-4541", "26-03-2018", "08:00", message);
    }

    @Test
    public void invalidTimeTest() {

        String message = "Invalid time. Please make sure you provide a time in 24-hour format: HH:mm";

        assertPicoYPlacaMessage("PTY-4541", "26/03/2018", "88:88", message);
    }
}
