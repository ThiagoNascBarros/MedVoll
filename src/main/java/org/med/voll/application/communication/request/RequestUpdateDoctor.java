package org.med.voll.application.communication.request;

import jakarta.validation.constraints.NotNull;
import org.med.voll.application.communication.request.doctor.RequestRegisterDoctorAddress;

public record RequestUpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        RequestRegisterDoctorAddress endereco) {
}
