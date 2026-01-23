package org.med.voll.communication.request.patient;

public record RequestRegisterPatient(String nome,
                                     String email,
                                     String cpf,
                                     String telefone,
                                     RequestRegisterPatientAddress endereco) {
}
