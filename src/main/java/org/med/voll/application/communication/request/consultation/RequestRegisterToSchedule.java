package org.med.voll.application.communication.request.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.med.voll.domain.consultation.Consultation;
import org.med.voll.domain.doctor.enums.ESpecialty;

import java.time.LocalDateTime;

public record RequestRegisterToSchedule(
        Long idPatient,

        Long idDoctor,

        ESpecialty specialty,

        @NotNull
        @Future
        LocalDateTime date
) {
    public RequestRegisterToSchedule(Consultation consultation) {
        this(
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getSpecialty(),
                consultation.getDate()
        );
    }
}
