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
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4542", "26/03/2018", "08:00");
//            boolean hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4542", "26/03/2018", "08:00");
            assertNotNull(picoYPlaca);
            picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4541", "26/03/2018", "19:30");
            assertNotNull(picoYPlaca);
            picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4541", "26/03/2018", "09:30");
            assertNotNull(picoYPlaca);
            picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build("PTY-4541", "26/03/2018", "07:00");
            assertNotNull(picoYPlaca);
        } catch (ValidationException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void doesNotHavePicoYPlacaTest() {
        try {
            boolean hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4543", "26/03/2018", "08:00");
            assertFalse(hasPicoyPlaca);
            hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4541", "27/03/2018", "19:30");
            assertFalse(hasPicoyPlaca);
            hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4541", "26/03/2018", "09:31");
            assertFalse(hasPicoyPlaca);
            hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4541", "26/03/2018", "06:59");
            assertFalse(hasPicoyPlaca);
            hasPicoyPlaca = picoYPlacaValidator.validate("PTY-4541", "26/03/2018", "11:00");
            assertFalse(hasPicoyPlaca);
        } catch (ValidationException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void invalidLicensePlateTest() {
        try {
            picoYPlacaValidator.validate("asdf", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
        try {
            picoYPlacaValidator.validate("PTY-4541-1", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
        try {
            picoYPlacaValidator.validate("PTY4541", "26/03/2018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid license plate"));
        }
    }

    @Test
    public void invalidDateTest() {
        try {
            picoYPlacaValidator.validate("PTY-4541", "26032018", "08:00");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid date"));
        }
    }

    @Test
    public void invalidTimeTest() {
        try {
            picoYPlacaValidator.validate("PTY-4541", "26/03/2018", "88:88");
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Invalid time"));
        }
    }
}
