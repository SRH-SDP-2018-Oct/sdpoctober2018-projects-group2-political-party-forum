package com.politicalforum.beans;

import java.util.List;

public class GeneralUser extends User {

	private String aadharNumber;

	public GeneralUser(String firstName, String lastName, int age, String emailId, String gender, Boolean isAnonymous,
			String region, String password, List<Group> groups, String aadharNumber) {
		super(firstName, lastName, age, emailId, gender, isAnonymous, region, password, groups);
		this.aadharNumber = aadharNumber;
	}

	public GeneralUser(String userId, String firstName, String lastName, int age, String emailId, String gender,
			Boolean isAnonymous, String region, List<Group> groups, String aadharNumber) {
		super(userId, firstName, lastName, age, emailId, gender, isAnonymous, region, groups);
		this.aadharNumber = aadharNumber;
	}

	
	
	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Override
	public String toString() {
		return "GeneralUser [aadharNumber=" + aadharNumber + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getAge()=" + getAge() + ", getEmailId()=" + getEmailId() + ", getGender()="
				+ getGender() + ", getIsAnonymous()=" + getIsAnonymous() + ", getRegion()=" + getRegion()
				+ ", getPassword()=" + getPassword() + ", getGroups()=" + getGroups() + ", getUserId()=" + getUserId()
				+ "]";
	}

}
