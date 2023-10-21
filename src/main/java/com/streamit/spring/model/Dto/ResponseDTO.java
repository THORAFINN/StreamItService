package com.streamit.spring.model.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseDTO {
	
	@JsonProperty(value = "code")
	private Integer code;
	
	@JsonProperty(value = "error_code")
	private String errorCode;
	
	@JsonProperty(value = "error_message")
	private String errorMessage;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ResponseDTO(Integer code, String errorCode, String errorMessage) {
		super();
		this.code = code;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public ResponseDTO (Integer code) {
		this.code = code;
	}
	
	public ResponseDTO() {
		super();
	}
	
	
	

}
