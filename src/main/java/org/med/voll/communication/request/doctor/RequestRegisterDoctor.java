package org.med.voll.communication.request.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.med.voll.domain.enums.ESpecialty;

public record RequestRegisterDoctor(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{10,20}")
        String telefone,

        @NotBlank()
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        ESpecialty especialidade,

        @NotNull @Valid
        RequestRegisterDoctorAddress endereco) {

}
