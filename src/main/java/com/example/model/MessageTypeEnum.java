package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public enum MessageTypeEnum {
	
	ERROR("error","Error"),
	INFO("info","Info"),
	WARNING("warning","Warning");
	
	private String msgType;
	private String msgDescription;
		
	public static MessageTypeEnum get(final String code) {
		for (MessageTypeEnum msgTypeEnum : MessageTypeEnum.values()){
			if (msgTypeEnum.getMsgType().equals(code)) {
				return msgTypeEnum;
			}			
		}
		return null;
	}
}
