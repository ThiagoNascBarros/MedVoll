package org.med.voll.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.med.voll.communication.request.doctor.RequestRegisterDoctor;
import org.med.voll.communication.response.ResponseGetAllDoctors;
import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private IDoctorRepository repository;

    @PostMapping
    @Transactional
    public void Register(@RequestBody @Valid RequestRegisterDoctor json) {
        repository.save(new Doctor(json));
    }

    @GetMapping
    public Page<ResponseGetAllDoctors> List(@PageableDefault(size = 5, sort = {"nome"}) Pageable page){
        return repository.findAll(page)
                .map(ResponseGetAllDoctors::new);
    }

}
