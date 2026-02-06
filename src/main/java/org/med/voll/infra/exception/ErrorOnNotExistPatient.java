package org.med.voll.infra.exception;

public class ErrorOnNotExistPatient extends IllegalArgumentException {
    public ErrorOnNotExistPatient(String message) {
        super(message);
    }
}
