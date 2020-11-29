package com.example.prwwsi2020.controllers;

import com.example.prwwsi2020.models.Patient;
import com.example.prwwsi2020.services.PatientService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    Flux<Patient> findAll(){
        return patientService.findAll()
                .doOnComplete(() -> log.info("Success executed '/patients' endpoint"))
                .doOnError(err -> log.error(err.getMessage()));
    }

    @GetMapping("/patient/{id}")
    Mono<ResponseEntity<Patient>> findById(@PathVariable String id){
        return patientService.findById(id).map(ResponseEntity::ok)
                .doOnSuccess(ex -> log.info("Return info of PatientId: " + id))
                .doOnError(err -> log.error(err.getMessage()))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping("patient/add")
    Mono<ResponseEntity<Patient>> addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient).map(ResponseEntity::ok)
                .doOnSuccess(ex -> log.info("Success add patient " + patient.getName() + " " + patient.getSurname()))
                .doOnError(err -> log.error(err.getMessage()));
    }


}
