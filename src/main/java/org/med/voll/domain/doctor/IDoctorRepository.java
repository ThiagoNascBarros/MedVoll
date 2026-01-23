package org.med.voll.domain.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, UUID> {
}
