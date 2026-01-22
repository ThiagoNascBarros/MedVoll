package org.med.voll.communication.request.doctor;

import org.med.voll.domain.ESpecialty;

public record RequestRegisterDoctor(String nome,
                                    String email,
                                    String crm,
                                    ESpecialty especialidade,
                                    RequestDoctorAddress endereco) {

}
