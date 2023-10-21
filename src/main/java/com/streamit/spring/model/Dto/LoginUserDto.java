package com.streamit.spring.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginUserDto {

	@JsonProperty(value = "user_name")
	private String userName;
	
	@JsonProperty(value = "password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
