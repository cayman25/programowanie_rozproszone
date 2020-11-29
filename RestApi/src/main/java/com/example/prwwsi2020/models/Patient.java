package com.example.prwwsi2020.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document("patients")
public class Patient {

    @Id
    String _id;

    @NotBlank(message = "Name is mandatory")
    String name;

    @NotBlank(message = "Surname is mandatory")
    String surname;

    @NotNull(message = "Age is mandatory")
    int age;

    @Email
    String email;

    LocalDate positive_test_date;
}
