package com.politicalforum.beans;

import java.util.List;

public class PoliticalUser extends User {

	private String politicianId;

	public PoliticalUser(String firstName, String lastName, int age, String emailId, String gender, Boolean isAnonymous,
			String region, String password, List<Group> groups, String politicianId) {
		super(firstName, lastName, age, emailId, gender, isAnonymous, region, password, groups);
		this.politicianId = politicianId;
	}

	public PoliticalUser(String userId, String firstName, String lastName, int age, String emailId, String gender,
			Boolean isAnonymous, String region, List<Group> groups, String politicianId) {
		super(userId, firstName, lastName, age, emailId, gender, isAnonymous, region, groups);
		this.politicianId = politicianId;
	}

	public String getPoliticianId() {
		return politicianId;
	}

	public void setPoliticianId(String politicianId) {
		this.politicianId = politicianId;
	}

	@Override
	public String toString() {
		return "PoliticalUser [politicianId=" + politicianId + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getAge()=" + getAge() + ", getEmailId()=" + getEmailId() + ", getGender()="
				+ getGender() + ", getIsAnonymous()=" + getIsAnonymous() + ", getRegion()=" + getRegion()
				+ ", getPassword()=" + getPassword() + ", getGroups()=" + getGroups() + ", getUserId()=" + getUserId()
				+ "]";
	}

}