package org.med.voll.domain.consultation.validators;

import br.com.fluentvalidator.AbstractValidator;
import org.med.voll.application.communication.request.consultation.RequestRegisterToSchedule;
import org.med.voll.domain.consultation.IConsultationRepository;
import org.med.voll.domain.doctor.IDoctorRepository;
import org.med.voll.domain.patient.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Predicate;

import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class ValidatorConsultationRequest extends AbstractValidator<RequestRegisterToSchedule> {

    @Autowired
    private IConsultationRepository consultationRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Override
    public void rules() {
        ruleFor(instance -> instance)
                .must(req -> req.date() != null && req.idPatient() != null).withMessage("Id da instance é nulo karalho")
                .must(req -> {
                    var primaryHour = req.date().withHour(7);
                    var endHour = req.date().withHour(18);
                    var randPatient = consultationRepository.existsByPatientIdAndDateBetween(req.idPatient(), primaryHour, endHour);

                    return !randPatient;
                }).withMessage("Paciente já possui uma consulta agendada neste dia");

        ruleFor(RequestRegisterToSchedule::idPatient)
                .must(Predicate.not(nullValue()))
                    .withMessage("Paciente está com id null");

        ruleFor(RequestRegisterToSchedule::idDoctor)
                .must(Predicate.not(nullValue()))
                .withMessage("Médico está com id nulo");

        ruleFor(RequestRegisterToSchedule::idDoctor)
                .must(Objects::nonNull).withMessage("Id da instance é nulo karalho")
                .must(d -> {
                    var doctor = doctorRepository.findByActiveDoctor(d);
                    if (doctor == null) {
                        throw new RuntimeException("Médico Nulo karalho");
                    }
                    return true;
                })
                .withMessage("Médico está inativo ou não existe");

        ruleFor(instance -> instance)
                .must(req -> req.idDoctor() != null && req.date() != null).withMessage("IDS nulos prr")
                .must(consultation -> {
                    var d = consultationRepository.existsByDoctorIdAndDate(consultation.idDoctor(), consultation.date());
                    return d == null;
                })
                .withMessage("Médico já tem uma consulta marcada com outro paciente");

        ruleFor(RequestRegisterToSchedule::date)
                .must(date -> date.isAfter(LocalDateTime.now().plusMinutes(30)))
                .withMessage("Consulta deve ser marcada com 30 minutos de antecedência");

        ruleFor(RequestRegisterToSchedule::date)
                .must(Predicate.not(nullValue()))
                .withMessage("Data da consulta é obrigatória")
                .must(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .withMessage("Consultas apenas de segunda-feira a sábado")
                .must(date -> date.getHour() >= 7 && date.getHour() < 18)
                .withMessage("Consulta fora do horário de funcionamento da clinica");

        ruleFor(RequestRegisterToSchedule::idPatient)
                .must(Predicate.not(nullValue()))
                .withMessage("Paciente está com id nulo");

        ruleFor(RequestRegisterToSchedule::idDoctor)
                .must(p -> doctorRepository.findByActivePatient(p))
                .withMessage("Médico está inativo ou não existe")
                .withFieldName("IsActive");
    }
}
