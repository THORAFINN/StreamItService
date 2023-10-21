package com.streamit.spring.model.Dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
 
public class UploadVideoandThumbNailDto {
	
	@JsonProperty("video")
	private MultipartFile video;
	
	@JsonProperty("thumbnail")
	private MultipartFile thumbNail;

	public MultipartFile getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(MultipartFile thumbNail) {
		this.thumbNail = thumbNail;
	}

	public MultipartFile getVideo() {
		return video;
	}

	public void setVideo(MultipartFile video) {
		this.video = video;
	}
		
	
}
