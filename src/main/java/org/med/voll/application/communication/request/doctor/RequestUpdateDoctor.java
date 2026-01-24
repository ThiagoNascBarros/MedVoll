package org.med.voll.application.communication.request.doctor;

import jakarta.validation.constraints.NotNull;

public record RequestUpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        RequestRegisterDoctorAddress endereco) {
}
