package org.med.voll.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.med.voll.application.communication.request.doctor.RequestUpdateDoctor;
import org.med.voll.application.communication.request.doctor.RequestRegisterDoctor;
import org.med.voll.application.communication.response.ResponseGetAllDoctors;
import org.med.voll.application.communication.response.ResponseInfoDoctor;
import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private IDoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity Register(@RequestBody @Valid RequestRegisterDoctor json, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(json);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctor/{id}")
                // Usando este método para referenciar o ID do doctor no path da URI criada
                .buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body( new ResponseInfoDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseGetAllDoctors>> List(@PageableDefault(size = 5, sort = {"nome"}) Pageable page){
        var paged = repository.findAllByIsActiveTrue(page)
                .map(ResponseGetAllDoctors::new);
        return ResponseEntity.ok(paged);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity Update(@RequestBody @Valid RequestUpdateDoctor request) {
        var doctor = repository.getReferenceById(request.id());
        doctor.updatingInfo(request);

        return ResponseEntity.ok(new ResponseInfoDoctor(doctor));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity Delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

}
