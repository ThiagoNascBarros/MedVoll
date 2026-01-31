package org.med.voll.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.med.voll.application.communication.request.consultation.RequestRegisterToSchedule;
import org.med.voll.application.communication.response.ResponseRegisterToSchedule;
import org.med.voll.domain.consultation.ToScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consultas")
public class ConsultationController {

    @Autowired
    private ToScheduleService service;


    @PostMapping
    @Transactional
    public ResponseEntity toSchedule(@Valid @RequestBody RequestRegisterToSchedule json) {
        service.executeSchedule(json);

        if (json == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new ResponseRegisterToSchedule(null, null, null, null));
    }

}
