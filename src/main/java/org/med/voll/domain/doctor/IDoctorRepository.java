package org.med.voll.domain.doctor;

import jakarta.validation.constraints.NotNull;
import org.med.voll.domain.consultation.Consultation;
import org.med.voll.domain.doctor.enums.ESpecialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByIsActiveTrue(Pageable page);

    @Query("""
            select d from Doctor d
            where d.isActive = true
            and d.especialidade = :specialty
            and d.id not in(
                select c.doctor.id from Consulta c where c.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor toChoosenDoctorFreelyOnDate(ESpecialty specialty, @NotNull LocalDateTime date);

    @Query("select d from Doctor d where d.id = :id and d.isActive = true")
    Doctor findByActiveDoctor(@Param("id") Long id);

    @Query("select d.isActive from Doctor d where d.id = :id")
    boolean findByActivePatient(@Param("id") Long id);
}
