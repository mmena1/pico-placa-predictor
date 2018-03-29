package org.predictor.picoplaca.main;

import org.predictor.picoplaca.builder.PicoYPlacaBuilder;
import org.predictor.picoplaca.builder.PicoYPlacaMessageBuilder;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.model.PicoYPlaca;
import org.predictor.picoplaca.validation.Validators;

import java.util.ArrayList;
import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static void main(String[] args) {

        try {
            if (args.length != 3) {
                System.err.println("Please provide exactly 3 arguments: A license plate number, a date and a time.");
                return;
            }
            List<String> messages = new ArrayList<>();
            PicoYPlaca picoYPlaca = PicoYPlacaBuilder.getINSTANCE().build(args[0], args[1], args[2], messages);
            if (picoYPlaca == null) {
                messages.forEach(System.out::println);
                return;
            }
            Validators.validate(picoYPlaca);
            System.out.println(PicoYPlacaMessageBuilder.getINSTANCE().hasPicoYPlaca(picoYPlaca));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
