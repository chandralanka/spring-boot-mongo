package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@ApiModel
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Document
public class Person {

    @Id
    @NotNull
    @ApiModelProperty(required = true, notes = "id", example = "100")
    private String id;

    @NotBlank
    @Size(min = 1, max = 20)
    @ApiModelProperty(required = true, notes = "First Name", example = "John")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 20)
    @ApiModelProperty(required = true, notes = "Last Name", example = "Doe")
    private String lastName;

    @Min(0)
    @Max(100)
    @ApiModelProperty(required = true, notes = "age", example = "25")
    private int age;

}