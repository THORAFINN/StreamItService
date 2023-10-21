package com.streamit.spring.model.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Source {
	
	private String settings;
	
	private List<Integer> validApis;
	
	public Source (String settings) throws  JsonProcessingException {
		this.settings = settings;
		ObjectMapper mapper = new ObjectMapper();
		List<SrDto> srtDto = mapper.readValue(settings , new TypeReference<List<SrDto>>() { });
		SrDto srDto = srtDto.get(0);
		validApis = new ArrayList<>();
		List<keyValueEntryDto> validLans = srDto.getValidLans();
		for (keyValueEntryDto dto : validLans) {
			 String iActive = (String) dto.getValue();
			 if (iActive.equalsIgnoreCase("true")) {
				 validApis.add(Integer.parseInt(dto.getKey()));
			 }
		}
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public List<Integer> getValidApis() {
		return validApis;
	}

	public void setValidApis(List<Integer> validApis) {
		this.validApis = validApis;
	}
	
	

}
