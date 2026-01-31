package org.med.voll.application.communication.response;

import java.time.LocalDateTime;

public record ResponseRegisterToSchedule(Long id, Long idPatient, Long idDoctor, LocalDateTime date) {

    public ResponseRegisterToSchedule(Long id, Long idPatient, Long idDoctor, LocalDateTime date) {
        this.id = id;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.date = date;
    }
}
