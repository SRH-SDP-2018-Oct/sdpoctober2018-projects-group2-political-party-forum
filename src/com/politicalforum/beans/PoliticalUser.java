package com.politicalforum.beans;

public class PoliticalUser {

	private int politicalUserId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String politicianId;
	private String gender;
	private int age;
	private String region;
	private Boolean isAnonymous;

	public PoliticalUser() {
	}

	public PoliticalUser(int politicalUserId, String firstName, String lastName, String emailId, String politicianId,
			String gender, int age, String region, Boolean isAnnonymous) {
		super();
		this.politicalUserId = politicalUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.politicianId = politicianId;
		this.gender = gender;
		this.age = age;
		this.region = region;
		this.isAnonymous = isAnnonymous;
	}

	public PoliticalUser(String firstName, String lastName, String emailId, String politicianId, String gender, int age,
			String region, Boolean isAnnonymous) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.politicianId = politicianId;
		this.gender = gender;
		this.age = age;
		this.region = region;
		this.isAnonymous = isAnnonymous;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPoliticianId() {
		return politicianId;
	}

	public void setPoliticianId(String politicianId) {
		this.politicianId = politicianId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnnonymous(Boolean isAnnonymous) {
		this.isAnonymous = isAnnonymous;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "PoliticalUser [politicalUserId=" + politicalUserId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", politicianId=" + politicianId + ", gender=" + gender + ", age="
				+ age + ", region=" + region + ", isAnnonymous=" + isAnonymous + "]";
	}

}