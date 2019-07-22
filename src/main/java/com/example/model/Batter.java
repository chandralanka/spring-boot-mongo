package com.example.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
//@Document
public class Batter {

    private String id;
    private String type;

}
