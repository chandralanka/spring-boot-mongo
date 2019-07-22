package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@ApiModel
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
//@Document
public class Batters {

    private List<Batter> batter = null;

}