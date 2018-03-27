package org.predictor.picoplaca.validator;

import org.junit.Test;
import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.exception.ValidationException;
import org.predictor.picoplaca.model.PicoYPlaca;

import static org.junit.Assert.*;

public class PicoYPlacaValidatorTest {

    private PicoYPlacaValidator picoYPlacaValidator = PicoYPlacaValidator.getINSTANCE();

    @Test
    public void hasPicoYPlacaTest() {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build("PTY-4542", "26/03/2018", "08:00");
//            boolean hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4542", "26/03/2018", "08:00");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "19:30");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "09:30");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "07:00");
            assertNotNull(picoYPlaca);
        } catch (ValidationException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void doesNotHavePicoYPlacaTest() {
        try {
            PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
            PicoYPlaca picoYPlaca = builder.build("PTY-4543", "26/03/2018", "08:00");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "27/03/2018", "19:30");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "09:31");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "06:59");
            assertNotNull(picoYPlaca);
            picoYPlaca = builder.build("PTY-4541", "26/03/2018", "11:00");
            assertNotNull(picoYPlaca);
        } catch (ValidationException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void invalidLicensePlateTest() {
        PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
        try {
            builder.build("asdf", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
        try {
            builder.build("PTY-4541-1", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
        try {
            builder.build("PTY4541", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
    }

    @Test
    public void invalidDateTest() {
        PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
        try {
            builder.build("PTY-4541", "26032018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid date"));
        }
    }

    @Test
    public void invalidTimeTest() {
        PicoYPlacaBuilder builder = PicoYPlacaBuilder.getINSTANCE();
        try {
            builder.build("PTY-4541", "26/03/2018", "88:88");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid time"));
        }
    }
}
