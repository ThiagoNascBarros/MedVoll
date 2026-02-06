package org.med.voll.infra.exception;

public class ErrorOnNotExistDoctor extends IllegalArgumentException {
    public ErrorOnNotExistDoctor(String message) {
        super(message);
    }
}
