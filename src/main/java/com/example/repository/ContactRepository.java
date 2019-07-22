package com.example.repository;

import com.example.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Contact findByName(String name);
}
