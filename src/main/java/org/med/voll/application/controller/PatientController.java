package org.med.voll.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.med.voll.application.communication.request.patient.RequestRegisterPatient;
import org.med.voll.domain.patient.IPatientRepository;
import org.med.voll.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private IPatientRepository repository;

    @PostMapping()
    @Transactional
    public void Register(@RequestBody @Valid RequestRegisterPatient request){
        repository.save(new Patient(request));
    }

}
