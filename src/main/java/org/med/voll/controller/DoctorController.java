package org.med.voll.controller;

import lombok.extern.slf4j.Slf4j;
import org.med.voll.communication.request.doctor.RequestRegisterDoctor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @PostMapping
    public void Register(@RequestBody RequestRegisterDoctor json) {
        log.info(json.toString());
    }

}
