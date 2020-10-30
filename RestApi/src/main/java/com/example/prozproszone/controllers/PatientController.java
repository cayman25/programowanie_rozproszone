package com.example.prozproszone.controllers;

import com.example.prozproszone.models.Patient;
import com.example.prozproszone.repositories.PatientRepository;
import com.example.prozproszone.services.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

@Log4j2
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    Flux<Patient> findAll(){
        return patientService.findAll();
    }

    @GetMapping("/patient/{id}")
    Mono<ResponseEntity<Patient>> findById(@PathVariable String id){
        return patientService.findById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("patient/add")
    Mono<ResponseEntity<Patient>> addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient).map(ResponseEntity::ok);
    }


}
