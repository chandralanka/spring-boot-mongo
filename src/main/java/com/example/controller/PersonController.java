package com.example.controller;

import com.example.model.Person;
import com.example.repository.PersonRespository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Person apis")
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonRespository personRespository;

    @ApiOperation(value = " API to retrieve all Person entities")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Server Error") })
    @GetMapping(value="/all")
    public List<Person> findAll() {
        return personRespository.findAll();
    }

    @ApiOperation(value = " API to retrieve a Person entity")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Server Error") })
    @GetMapping(value="/{firstName}")
    public List<Person> findByFirstName(@PathVariable String firstName) {
        return personRespository.findByFirstName(firstName);
    }

    @ApiOperation(value = " API to create Person Entity")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Server Error") })
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personRespository.save(person);
    }    

	@ApiOperation(value = " API to update Person entity")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Server Error") })
    @PutMapping
    public Person update(@RequestBody Person person) {
        return personRespository.save(person);
    }

    @ApiOperation(value = " API to Delete a Person entity")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Server Error") })
    @DeleteMapping
    public void delete(@RequestBody String id) {
        personRespository.deleteById(id);
    }
}