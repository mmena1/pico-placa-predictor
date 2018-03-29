package org.predictor.picoplaca.builder;

import org.junit.Test;
import org.predictor.picoplaca.model.PicoYPlaca;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class PicoYPlacaBuilderTest {

    private void assertCorrectBuild(String licensePlate, String date, String time, List<String> messages) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time, messages);
            assertThat(picoYPlaca, notNullValue());
            assertThat(messages.isEmpty(), is(true));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void correctBuildTest() {

        assertCorrectBuild("PTY-4542", "26/03/2018", "08:00", new ArrayList<>());
        assertCorrectBuild("PTY-4541", "26/03/2018", "19:30", new ArrayList<>());
        assertCorrectBuild("PTY-4541", "26/03/2018", "09:30", new ArrayList<>());
        assertCorrectBuild("PTY-454", "29/03/2018", "17:00", new ArrayList<>());

    }

    private void assertIncorrectBuild(String licensePlate, String date, String time, String message) {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            List<String> outputMessages = new ArrayList<>();
            PicoYPlaca picoYPlaca = builder.build(licensePlate, date, time, outputMessages);
            assertThat(picoYPlaca, nullValue());
            assertThat(outputMessages.size(), is(1));
            assertThat(outputMessages.get(0), containsString(message));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void invalidLicensePlateTest() {

        String message = "Invalid license plate. Please make sure you provide a license plate with the following format: ABC-1234 or ABC-123";

        assertIncorrectBuild("asdf", "26/03/2018", "08:00", message);
        assertIncorrectBuild("PTY-4541-1", "26/03/2018", "08:00", message);
        assertIncorrectBuild("PTY4541", "26/03/2018", "08:00", message);
    }

    @Test
    public void invalidDateTest() {

        String message = "Invalid date. Please make sure you provide a date in format dd/MM/yyyy";

        assertIncorrectBuild("PTY-4541", "26032018", "08:00", message);
        assertIncorrectBuild("PTY-4541", "26-03-2018", "08:00", message);
    }

    @Test
    public void invalidTimeTest() {

        String message = "Invalid time. Please make sure you provide a time in 24-hour format: HH:mm";

        assertIncorrectBuild("PTY-4541", "26/03/2018", "88:88", message);
    }

    @Test
    public void multipleInvalidMessagesTest() {
        String message1 = "Invalid license plate. Please make sure you provide a license plate with the following format: ABC-1234 or ABC-123";
        String message2 = "Invalid date. Please make sure you provide a date in format dd/MM/yyyy";
        String message3 = "Invalid time. Please make sure you provide a time in 24-hour format: HH:mm";
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            List<String> outputMessages = new ArrayList<>();
            PicoYPlaca picoYPlaca = builder.build("PTYass", "444435", "sdfs", outputMessages);
            assertThat(picoYPlaca, nullValue());
            assertThat(outputMessages.size(), is(3));
            assertThat(outputMessages.get(0), containsString(message1));
            assertThat(outputMessages.get(1), containsString(message2));
            assertThat(outputMessages.get(2), containsString(message3));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
