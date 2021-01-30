package com.mobiledeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -1144578871980675731L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false , length = 50)
	private String firstName;
	
	@Column(nullable = false , length = 50)
	private String lastName;
	
	@Column(nullable = false , length = 120)
	private String email;
	
	@Column(nullable = false )
	private String encreptedPassword;
	private String emailvarificationStatus;
	
	@Column(nullable = false)
	private Boolean emailvarificationTocken = false;

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
