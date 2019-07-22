package com.example.repository;

import com.example.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRespository extends MongoRepository<Person, String> {
    List<Person> findByFirstName(String firstName);
}
