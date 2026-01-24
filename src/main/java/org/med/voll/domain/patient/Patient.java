package org.med.voll.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.med.voll.application.communication.request.patient.RequestRegisterPatient;
import org.med.voll.application.communication.request.patient.RequestUpdatePatient;
import org.med.voll.domain.address.Address;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean isActive;

    @Embedded
    private Address endereco;

    public Patient(RequestRegisterPatient json) {
        this.nome = json.nome();
        this.email = json.email();
        this.telefone = json.telefone();
        this.cpf = json.cpf();
        this.endereco = new Address(json.endereco());
        this.isActive = true;
    }
}
