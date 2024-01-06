package com.streamit.spring.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserDto {
	
	@JsonProperty(value = "name", required = true) 
	private String name;
	
	@JsonProperty(value = "user_name", required = true)
	private String userName;
	
	@JsonProperty(value = "password", required = true)
	private String password;
	
	@JsonProperty(value = "email", required = true)
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RegisterUserDto(String name, String userName, String password, String email) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
}
