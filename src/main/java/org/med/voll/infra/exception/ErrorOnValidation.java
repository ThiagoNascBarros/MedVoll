package org.med.voll.infra.exception;

import java.util.List;

public class ErrorOnValidation extends RuntimeException {
    public ErrorOnValidation(String s, List<String> errors) {
        super(s);
    }
}
