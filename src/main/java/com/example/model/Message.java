package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value=Include.NON_EMPTY)
@Getter @Setter @NoArgsConstructor
@JsonPropertyOrder({"level", "details"})
public class Message {	
	
	private MessageTypeEnum level;	
	
    private List<String>  details = new ArrayList<>();
    @JsonIgnore
    private List<Integer> codes = new ArrayList<>();
    
    public void addErrorCode(Integer errorCode) {
    	if(!this.codes.contains(errorCode)) {
    		this.codes.add(errorCode);
    	}
    }
    
    public void addDetails(String message) {
    	if(!this.details.contains(message)) {
    		this.details.add(message);
    	}
    }
}