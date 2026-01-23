package org.med.voll.communication.response;

import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.enums.ESpecialty;

public record ResponseGetAllDoctors(String nome,
                                    String email,
                                    String crm,
                                    ESpecialty especialidade) {
    public ResponseGetAllDoctors(Doctor d) {
        this
        (
                d.getNome(),
                d.getEmail(),
                d.getCrm(),
                d.getEspecialidade()
        );
    }
}
