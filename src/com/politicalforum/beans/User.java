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
	private Boolean isAnonymous;
	private String region;
	private String password;
	private List<Group> groups = new ArrayList<>();
	private Group selectedGroup = null;
	
	public User() {
		super();
	}

	public User(String userId, String firstName, String lastName, int age, String emailId, String gender,
			Boolean isAnonymous, String region, List<Group> groups) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.isAnonymous = isAnonymous;
		this.region = region;
		this.groups = groups;
	}

	public User(String firstName, String lastName, int age, String emailId, String gender, Boolean isAnonymous,
			String region, String password, List<Group> groups) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.isAnonymous = isAnonymous;
		this.region = region;
		this.password = password;
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getUserId() {
		return userId;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", emailId=" + emailId + ", gender=" + gender + ", isAnonymous=" + isAnonymous + ", region=" + region
				+ ", groups=" + groups + "]";
	}

}
