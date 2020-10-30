package model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Patient {

    String _id;
    String name;
    String surname;
    int age;
    String positive_test_date;
    String email;

    @Override
    public String toString() {
        return "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email=" + email +
                ", positive_test_date='" + positive_test_date + '\'';
    }
}
