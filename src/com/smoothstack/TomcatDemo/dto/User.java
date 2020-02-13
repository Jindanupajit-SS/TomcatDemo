package com.smoothstack.TomcatDemo.dto;

public class User {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int userId, String firstName, String lastName) {
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer id) {
		this.userId = id;
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
	
	

}
