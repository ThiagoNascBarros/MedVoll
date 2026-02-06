package org.med.voll.infra.exception;

public class ErrorOnSpecialtyBlankOrNull extends RuntimeException {
    public ErrorOnSpecialtyBlankOrNull(String message) {
        super(message);
    }
}
