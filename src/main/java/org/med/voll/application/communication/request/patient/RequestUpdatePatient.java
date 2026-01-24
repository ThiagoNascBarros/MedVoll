package org.med.voll.application.communication.request.patient;

import jakarta.validation.constraints.NotNull;

public record RequestUpdatePatient(
        @NotNull
        Long id,
        String nome,
        String telefone,
        RequestUpdatePatientAddress endereco) {
}
