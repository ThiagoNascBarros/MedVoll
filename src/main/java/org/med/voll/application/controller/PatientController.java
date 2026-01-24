package org.med.voll.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.med.voll.application.communication.request.patient.RequestUpdatePatient;
import org.med.voll.application.communication.request.patient.RequestRegisterPatient;
import org.med.voll.application.communication.response.ResponseGetAllPatient;
import org.med.voll.domain.patient.IPatientRepository;
import org.med.voll.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private IPatientRepository repository;

    @PostMapping()
    @Transactional
    public void Register(@RequestBody @Valid RequestRegisterPatient request) {
        repository.save(new Patient(request));
    }

    @GetMapping()
    public Page<ResponseGetAllPatient> GetAll(@PageableDefault(size = 15, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByIsActiveTrue(pageable)
                .map(ResponseGetAllPatient::new);
    }

    @PutMapping()
    @Transactional
    public void Update(@RequestBody @Valid RequestUpdatePatient request) {
        var patient = repository.findById(request.id());
        patient.ifPresent(p -> {
            p.updateInfo(request);
            log.info("O paciente com id {} foi atualizado", request.id());
        });
    }

    @DeleteMapping("{id}")
    @Transactional
    public void Delete (@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }

}
