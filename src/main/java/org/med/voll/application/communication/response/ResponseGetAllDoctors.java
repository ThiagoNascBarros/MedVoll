package org.med.voll.application.communication.response;

import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.enums.ESpecialty;

public record ResponseGetAllDoctors(
        Long id,
        String nome,
        String email,
        String crm,
        ESpecialty especialidade) {
    public ResponseGetAllDoctors(Doctor d) {
        this
                (
                        d.getId(),
                        d.getNome(),
                        d.getEmail(),
                        d.getCrm(),
                        d.getEspecialidade()
                );
    }
}
