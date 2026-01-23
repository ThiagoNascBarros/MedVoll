package org.med.voll.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByIsActiveTrue(Pageable page);
}
