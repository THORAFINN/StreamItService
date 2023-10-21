package com.streamit.spring.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileDataDto {
	
	@JsonProperty("file_name")
	private String fileName;
	
	@JsonProperty("file_path")
	private String filePath;

	public FileDataDto(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
