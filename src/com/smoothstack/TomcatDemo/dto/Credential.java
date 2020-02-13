package com.smoothstack.TomcatDemo.dto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;

public class Credential {

	
	private int credentialId;
	private User user;
	private String username;
	private String password;
	
	
	public Credential() {
		// TODO Auto-generated constructor stub
	}
	public Credential(int credentialId, User user, String login, String password) {
		// TODO Auto-generated constructor stub
		
		setCredentialId(credentialId);
		setUser(user);
		setUsername(login);
		setPassword(password);
	}
	
	public int getCredentialId() {
		return credentialId;
	}
	
	public void setCredentialId(int credentialId) {
		this.credentialId = credentialId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String login) {
		this.username = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
