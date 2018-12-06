package com.politicalforum.beans;

import java.util.ArrayList;
import java.util.List;

public class PoliticalUser {

	private String politicalUserId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String politicianId;
	private String gender;
	private int age;
	private String region;
	private Boolean isAnonymous;
	private String password;
	private List<Group> groups = new ArrayList<>();
	
	public PoliticalUser() {
	}

	public PoliticalUser(String politicalUserId, String firstName, String lastName, String emailId, String politicianId,
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
			String region, Boolean isAnnonymous, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.politicianId = politicianId;
		this.gender = gender;
		this.age = age;
		this.region = region;
		this.isAnonymous = isAnnonymous;
		this.password = password;
	}

	public PoliticalUser(String politicalUserId, String firstName, String lastName, String emailId, String politicianId,
			String gender, int age, String region, Boolean isAnonymous, List<Group> groups) {
		super();
		this.politicalUserId = politicalUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.politicianId = politicianId;
		this.gender = gender;
		this.age = age;
		this.region = region;
		this.isAnonymous = isAnonymous;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoliticalUserId() {
		return this.politicalUserId;
	}
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	@Override
	public String toString() {
		return "PoliticalUser [politicalUserId=" + politicalUserId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", politicianId=" + politicianId + ", gender=" + gender + ", age="
				+ age + ", region=" + region + ", isAnonymous=" + isAnonymous + ", password=" + password + ", groups="
				+ groups + "]";
	}

}