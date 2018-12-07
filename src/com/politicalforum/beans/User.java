package com.politicalforum.beans;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String firstName;
	private String lastName;
	private int age;
	private String emailId;
	private String gender;
	private String aadharNumber;
	private Boolean isAnonymous;
	private String region;
	private String password;
	private List<Group> groups = new ArrayList<>();
	
	
	public User(String userId, String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.isAnonymous = isAnonymous;
		this.region = region;
	}

	public User(String firstName, String lastName, int age, String emailId, String gender, String aadharNumber,
			Boolean isAnonymous, String region, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.isAnonymous = isAnonymous;
		this.region = region;
		this.password = password;
	}
	
	public User(String userId, String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, List<Group> groups) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.isAnonymous = isAnonymous;
		this.region = region;
		this.groups = groups;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return this.userId;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", emailId=" + emailId + ", gender=" + gender + ", aadharNumber=" + aadharNumber + ", isAnonymous="
				+ isAnonymous + ", region=" + region + ", password=" + password + ", groups=" + groups + "]";
	}

}
