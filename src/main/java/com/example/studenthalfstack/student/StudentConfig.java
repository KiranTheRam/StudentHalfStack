package com.example.studenthalfstack.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student kiran = new Student(
                    "kiran",
                    "kiran@email.com",
                    21
            );
            Student luke = new Student(
                    "luke",
                    "luke@email.com",
                    17
            );
            repository.saveAll(List.of(kiran, luke));
        };

    }
}
