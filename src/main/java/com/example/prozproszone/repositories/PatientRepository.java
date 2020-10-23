package com.example.prozproszone.repositories;

import com.example.prozproszone.models.Patient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {

}
