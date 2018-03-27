package org.predictor.picoplaca.validator;

import org.predictor.picoplaca.model.PicoYPlaca;

public abstract class AbstractValidator {

    private PicoYPlaca picoYPlaca;

    abstract protected boolean validate();
}
