package org.med.voll.application.communication.response;

import org.med.voll.domain.address.Address;
import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.enums.ESpecialty;

import java.util.Optional;

public record ResponseInfoDoctor(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        ESpecialty especialidade,
        Address endereco) {
    public ResponseInfoDoctor(Doctor doctor) {
        this (
                doctor.getId(),
                doctor.getNome(),
                doctor.getEmail(),
                doctor.getTelefone(),
                doctor.getCrm(),
                doctor.getEspecialidade(),
                doctor.getEndereco()
        );
    }
}
