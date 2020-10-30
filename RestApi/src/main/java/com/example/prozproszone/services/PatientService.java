package com.example.prozproszone.services;

import com.example.prozproszone.models.Patient;
//import com.example.prozproszone.rabbitmq.RabbitmqConfiguration;
import com.example.prozproszone.rabbitmq.RabbitMqSender;
import com.example.prozproszone.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final RabbitMqSender sender;


    private PatientService(PatientRepository patientRepository, RabbitMqSender sender){ //}, RabbitmqConfiguration rabbitmqConfiguration) {
        this.patientRepository = patientRepository;
        this.sender = sender;
    }

    public Flux<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Mono<Patient> findById(String id){
        return patientRepository.findById(id);
    }

    public Mono<Patient> addPatient(Patient patient) {
        return patientRepository.save(patient).doOnNext(message -> addEmailToQueueMono(message.getEmail()));
    }

    private void addEmailToQueueMono(String email){
        sender.sendMono("realize_email", email);
    }
}

