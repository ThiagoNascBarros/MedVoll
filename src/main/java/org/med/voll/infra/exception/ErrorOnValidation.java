package org.med.voll.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorOnValidation extends RuntimeException {

    private List<String> errors;

    public ErrorOnValidation(String s, List<String> errors) {
        super(s);
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n" + this.errors.toString().replace("[", "").replace("]", "");
    }

    @Override
    public String toString() {
        return "\n" + errors;
    }
}
