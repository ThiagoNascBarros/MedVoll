package org.med.voll.application.communication.response;

import java.time.LocalDateTime;

public record ResponseRegisterToSchedule(Long id, Long idPatient, Long idDoctor, LocalDateTime date) {
}
