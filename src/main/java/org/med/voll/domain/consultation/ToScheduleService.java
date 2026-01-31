package org.med.voll.domain.consultation;

import org.med.voll.application.communication.request.consultation.RequestRegisterToSchedule;
import org.med.voll.domain.doctor.IDoctorRepository;
import org.med.voll.domain.patient.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToScheduleService {

    @Autowired
    private IConsultationRepository repository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IPatientRepository patientRepository;

    public void executeSchedule(RequestRegisterToSchedule request) {


        var patient = this.patientRepository.findById(request.idPatient()).get();
        var doctor = this.doctorRepository.findById(request.idDoctor()).get();

        var consultation = new Consultation(null, doctor, patient, request.date());

        repository.save(result);
    }

}
