package com.streamit.spring.model.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SrDto {
	
	@JsonProperty("valid_apis")
	private List<keyValueEntryDto> validLans;

	public List<keyValueEntryDto> getValidLans() {
		return validLans;
	}

	public void setValidLans(List<keyValueEntryDto> validLans) {
		this.validLans = validLans;
	}
	
	

}
