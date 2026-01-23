package org.med.voll.communication.request.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record RequestRegisterPatient(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String telefone,

        @NotNull
        @Valid
        RequestRegisterPatientAddress endereco) {
}
