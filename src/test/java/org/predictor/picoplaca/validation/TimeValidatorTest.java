package org.predictor.picoplaca.validation;

import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.util.Status;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeValidatorTest {

    @Test
    public void successTest() {
        TimeValidator timeValidator = new TimeValidator();
        List<String> messages = new ArrayList<>();
        PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4542", "26/03/2018", "08:00", messages);
        timeValidator.validate(picoYPlaca);
        assertThat(messages.isEmpty(), is(true));
        assertThat(timeValidator.status.getStatus(), is(Status.SUCCESS));
    }

    @Test
    public void failTest() {
        TimeValidator timeValidator = new TimeValidator();
        List<String> messages = new ArrayList<>();
        PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4542", "26/03/2018", "11:00", messages);
        timeValidator.validate(picoYPlaca);
        assertThat(messages.isEmpty(), is(true));
        assertThat(timeValidator.status.getStatus(), is(Status.FAIL));
    }
}
