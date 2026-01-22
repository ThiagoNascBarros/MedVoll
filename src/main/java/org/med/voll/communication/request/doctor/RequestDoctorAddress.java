package org.med.voll.communication.request.doctor;

public record RequestDoctorAddress(String logradouro,
                                   String bairro,
                                   String cep,
                                   String cidade,
                                   String uf,
                                   String complemento,
                                   String numero) {
}
