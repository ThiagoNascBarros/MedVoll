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
@Table(name = "patient")
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

    public void updateInfo(RequestUpdatePatient request) {
        if (request.nome() != null) {
            this.nome = request.nome();
        }

        if (request.telefone() != null) {
            this.telefone = request.telefone();
        }

        if (request.endereco() != null) {
            this.endereco.updatingInfo(request.endereco());
        }
    }

    public void delete() {
        this.isActive = false;
    }
}
