package org.predictor.picoplaca.validation;

import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ValidatorsTest {

    private void assertPicoYPlacaFormattedMessage(String licensePlate, String date, String time, String message) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            List<String> outputMessages = new ArrayList<>();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time, outputMessages);
            assertThat(picoYPlaca, notNullValue());
            outputMessages.addAll(Validators.validate(picoYPlaca));
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

}
