package org.med.voll.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
}
