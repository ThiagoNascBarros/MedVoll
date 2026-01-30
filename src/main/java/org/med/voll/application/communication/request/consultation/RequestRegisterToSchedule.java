package org.med.voll.application.communication.request.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestRegisterToSchedule(
        Long idPatient,

        @NotNull
        Long idDoctor,

        @NotNull
        @Future
        LocalDateTime date
) {
}
