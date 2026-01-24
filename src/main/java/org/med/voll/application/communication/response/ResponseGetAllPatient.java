package org.med.voll.application.communication.response;

import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.patient.Patient;

public record ResponseGetAllPatient(
        Long id,
        String nome,
        String email,
        String cpf) {
    public ResponseGetAllPatient(Patient p) {
        this
                (
                        p.getId(),
                        p.getNome(),
                        p.getEmail(),
                        p.getCpf()
                );
    }
}
