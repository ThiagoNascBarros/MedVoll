package org.med.voll.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime primaryHour, LocalDateTime endHour);

    @Query("select c from Consulta c where c.doctor.id = :id and c.date = :date")
    Consultation existsByDoctorIdAndDate(Long id, LocalDateTime date);
}
