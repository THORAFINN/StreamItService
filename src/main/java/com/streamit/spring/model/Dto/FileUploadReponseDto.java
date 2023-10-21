package com.streamit.spring.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUploadReponseDto {

	@JsonProperty("code")
	private int code;
	
	@JsonProperty("errro_code")
	private String errorCode;
	
	@JsonProperty("error_,essage")
	private String errorMessage;
	
	@JsonProperty("video_file_path")
	private String videoFilePath;
	
	@JsonProperty("video_file_name")
	private String videoFileName;
	
	@JsonProperty("thumb_nail_file_path")
	private String thumnNailFilePath;
	
	@JsonProperty("thumb_nail_file_name")
	private String thumbNailFileName;

	public FileUploadReponseDto(int code, String errorCode, String errorMessage) {
		super();
		this.code = code;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public FileUploadReponseDto(int code, String videoFilePath, String videoFileName, String thumnNailFilePath,
			String thumbNailFileName) {
		super();
		this.code = code;
		this.videoFilePath = videoFilePath;
		this.videoFileName = videoFileName;
		this.thumnNailFilePath = thumnNailFilePath;
		this.thumbNailFileName = thumbNailFileName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	public String getVideoFilePath() {
		return videoFilePath;
	}

	public void setVideoFilePath(String videoFilePath) {
		this.videoFilePath = videoFilePath;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public String getThumnNailFilePath() {
		return thumnNailFilePath;
	}

	public void setThumnNailFilePath(String thumnNailFilePath) {
		this.thumnNailFilePath = thumnNailFilePath;
	}

	public String getThumbNailFileName() {
		return thumbNailFileName;
	}

	public void setThumbNailFileName(String thumbNailFileName) {
		this.thumbNailFileName = thumbNailFileName;
	} 
	
}
