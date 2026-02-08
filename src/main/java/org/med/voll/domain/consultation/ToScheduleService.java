package org.med.voll.domain.consultation;

import br.com.fluentvalidator.context.Error;
import lombok.extern.log4j.Log4j2;
import org.med.voll.application.communication.request.consultation.RequestRegisterToSchedule;
import org.med.voll.application.communication.response.Response;
import org.med.voll.domain.consultation.validators.ValidatorConsultationRequest;
import org.med.voll.domain.doctor.Doctor;
import org.med.voll.domain.doctor.IDoctorRepository;
import org.med.voll.domain.patient.IPatientRepository;
import org.med.voll.infra.exception.ErrorOnNotExistDoctor;
import org.med.voll.infra.exception.ErrorOnNotExistPatient;
import org.med.voll.infra.exception.ErrorOnSpecialtyBlankOrNull;
import org.med.voll.infra.exception.ErrorOnValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ToScheduleService {

    @Autowired
    private IConsultationRepository repository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private ValidatorConsultationRequest consultationRequest;

    public Response<RequestRegisterToSchedule> executeSchedule(RequestRegisterToSchedule request) {
        var result = new Response<RequestRegisterToSchedule>();
        var resultValid = this.consultationRequest.validate(request);

        if (!resultValid.isValid()) {
            var list = resultValid.getErrors().stream()
                    .map(Error::getMessage)
                    .toList();

            result.setErrors(list);

            throw  new ErrorOnValidation("Erros na validação do usuário: ", list);
        }

        if (!patientRepository.existsById(request.idPatient())){
            throw new ErrorOnNotExistPatient("Paciente não existe em nossa base de dados");
        }

        if (request.idDoctor() != null && !doctorRepository.existsById(request.idDoctor())){
            throw new ErrorOnNotExistDoctor("Médico não existe em nossa base de dados");
        }

        var patient = this.patientRepository.findById(request.idPatient()).get();
        var doctor = chosenDoctor(request);

        var consultation = new Consultation(doctor, patient, request.date(), request.specialty());

        repository.save(consultation);

        return result;
    }

    private Doctor chosenDoctor(RequestRegisterToSchedule request) {
        if (request.idDoctor() != null) {
            return doctorRepository.getReferenceById(request.idDoctor());
        }

        if (request.specialty() == null) {
            throw new ErrorOnSpecialtyBlankOrNull("Especialidade é obrigatória quando médico não for escolhido");
        }

        return doctorRepository.toChoosenDoctorFreelyOnDate(request.specialty(), request.date());
    }

}
