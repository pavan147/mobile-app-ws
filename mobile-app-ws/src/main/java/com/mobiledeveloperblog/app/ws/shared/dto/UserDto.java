package com.mobiledeveloperblog.app.ws.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

 

	/**
	 * 
	 */
	private static final long serialVersionUID = 2465698674612782845L;
	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encreptedPassword;
	private String emailvarificationStatus;
	private Boolean emailvarificationTocken =false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncreptedPassword() {
		return encreptedPassword;
	}

	public void setEncreptedPassword(String encreptedPassword) {
		this.encreptedPassword = encreptedPassword;
	}

	public String getEmailvarificationStatus() {
		return emailvarificationStatus;
	}

	public void setEmailvarificationStatus(String emailvarificationStatus) {
		this.emailvarificationStatus = emailvarificationStatus;
	}

	public Boolean getEmailvarificationTocken() {
		return emailvarificationTocken;
	}

	public void setEmailvarificationTocken(Boolean emailvarificationTocken) {
		this.emailvarificationTocken = emailvarificationTocken;
	}


	

}
