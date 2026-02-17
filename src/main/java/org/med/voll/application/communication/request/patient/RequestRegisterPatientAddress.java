package org.med.voll.application.communication.request.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestRegisterPatientAddress(@NotBlank
                                            String logradouro,

                                            @NotBlank
                                            String bairro,

                                            @NotBlank
                                            @Pattern(regexp = "^(\\d{5}-\\d{3}|\\d{8})$")
                                            String cep,

                                            @NotBlank
                                            String cidade,

                                            @NotBlank
                                            String uf,

                                            String complemento,
                                            String numero) {
}
