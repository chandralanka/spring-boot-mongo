package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Document(collection = "contacts")
public class Contact {
    @ApiModelProperty(required = true, notes = "id", example = "100")
    @Id
    String id;
    @ApiModelProperty(required = true, notes = "name", example = "Full name")
    String name;
    @ApiModelProperty(required = true, notes = "Address", example = "Addess")
    String address;
    @ApiModelProperty(required = true, notes = "itineraryId", example = "Ashburn")
    String city;
    @ApiModelProperty(required = true, notes = "Phone", example = "111-111-1111")
    String phone;
    @ApiModelProperty(required = true, notes = "email", example = "me@example.com")
    String email;
}
