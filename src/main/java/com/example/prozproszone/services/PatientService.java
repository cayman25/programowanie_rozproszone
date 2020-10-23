package com.example.prozproszone.services;

import com.example.prozproszone.models.Patient;
import com.example.prozproszone.repositories.PatientRepository;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Mono<Patient> findById(String id){
        return patientRepository.findById(id);
    }

    public Mono<Patient> addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    private Mono<Patient> validPatientOrError(@NonNull Patient patient) {
        return (patient.get_id() == null)
                ? monoBadRequestError("User ID cannot be null or blank")
                : Mono.defer(() -> Mono.just(patient));
    }

    private Mono<Patient> monoBadRequestError(@NonNull String message) {
        return Mono.error(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, message));
    }
}
