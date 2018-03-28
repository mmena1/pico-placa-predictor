package org.predictor.picoplaca.validation;

import org.predictor.picoplaca.conversion.DateTimeConverter;
import org.predictor.picoplaca.exception.ConversionException;
import org.predictor.picoplaca.util.PropertiesLoader;

import java.time.LocalDate;
import java.time.LocalTime;

public class InputValidator {


    public void validate(String licensePlate, String date, String time) {
        licensePlate.matches(PropertiesLoader.getProperties().getProperty("regex.license.plate"));
        DateTimeConverter converter = DateTimeConverter.getINSTANCE();
        try {
            LocalDate localDate = converter.convertToDate(date);
        } catch (ConversionException e) {
            e.printStackTrace();
        }
        try {
            LocalTime localTime = converter.convertToTime(time);
        } catch (ConversionException e) {
            e.printStackTrace();
        }

    }
}
