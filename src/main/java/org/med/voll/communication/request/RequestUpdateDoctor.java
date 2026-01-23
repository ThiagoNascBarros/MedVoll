package org.med.voll.communication.request;

import jakarta.validation.constraints.NotNull;
import org.med.voll.communication.request.doctor.RequestRegisterDoctorAddress;

public record RequestUpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        RequestRegisterDoctorAddress endereco) {
}
