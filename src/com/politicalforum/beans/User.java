package com.politicalforum.beans;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int age;
	private String emailId;
	private String gender;
	private String aadharNumber;
	private Boolean isAnonymous;

	public User(int userId, String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.isAnonymous = isAnonymous;
	}

	public User(String firstName, String lastName, int age, String emailId, String gender, String aadharNumber,
			Boolean isAnonymous) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailId = emailId;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.isAnonymous = isAnonymous;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", emailId=" + emailId + ", gender=" + gender + ", aadharNumber=" + aadharNumber + ", isAnonymous="
				+ isAnonymous + "]";
	}

}
