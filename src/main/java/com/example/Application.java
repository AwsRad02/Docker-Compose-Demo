package com.example;

// Imports 
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.Application.PersonRecord;

import java.time.Instant;
import java.util.List;

//springboot entry point
// mongo repository
interface PersonRepo extends MongoRepository<PersonRecord, String> {
    List<PersonRecord> findTop10ByOrderByCreatedAtDesc();
}

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // data model
    @Document("people")
    static class PersonRecord {
        @Id
        public String id;

        @NotBlank
        public String name;
        @NotBlank
        @Email
        public String email;

        @Min(1)
        @Max(120)
        public Integer age;

        public Instant createdAt = Instant.now();
    }

    // rest API controller
    @RestController
    @RequestMapping("/api/records")
    @CrossOrigin
    static class PersonApi {
        private final PersonRepo repo;

        PersonApi(PersonRepo repo) {
            this.repo = repo;
        }

        @GetMapping
        public List<PersonRecord> latest() {
            return repo.findTop10ByOrderByCreatedAtDesc();
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public PersonRecord create(@Valid @RequestBody PersonRecord body) {
            body.id = null;
            body.createdAt = Instant.now();
            return repo.save(body);
        }
    }
}