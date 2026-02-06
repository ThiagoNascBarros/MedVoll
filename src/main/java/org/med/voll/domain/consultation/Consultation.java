package org.med.voll.domain.consultation;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.enums.ESpecialty;
import org.med.voll.domain.patient.Patient;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Consulta")
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private ESpecialty specialty;

    private LocalDateTime date;

    public Consultation(Doctor doctor, Patient patient, @NotNull @Future LocalDateTime date, ESpecialty specialty) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.specialty = specialty;
    }
}
