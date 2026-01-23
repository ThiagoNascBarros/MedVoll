package org.med.voll.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.med.voll.communication.request.RequestUpdateDoctor;
import org.med.voll.communication.request.doctor.RequestRegisterDoctor;
import org.med.voll.domain.address.Address;
import org.med.voll.domain.doctor.enums.ESpecialty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "doctors")
@Entity(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private ESpecialty especialidade;
    @Embedded
    private Address endereco;

    public Doctor(RequestRegisterDoctor json) {
        this.nome = json.nome();
        this.email = json.email();
        this.telefone = json.telefone();
        this.crm = json.crm();
        this.especialidade = json.especialidade();
        this.endereco = new Address(json.endereco());
    }

    public void updatingInfo(RequestUpdateDoctor request) {
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
}
