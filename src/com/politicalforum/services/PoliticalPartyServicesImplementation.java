package com.politicalforum.services;

import java.sql.SQLException;
import java.sql.Date;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyDAOServicesProvider;

public class PoliticalPartyServicesImplementation implements PoliticalPartyServices {
	PoliticalPartyDAOServices politicalPartyDaoServices;

	public PoliticalPartyServicesImplementation() throws ServiceNotFoundException {
		politicalPartyDaoServices = PoliticalPartyDAOServicesProvider.getPoliticalPartyDAOServicesImplementor();
	}

	@Override
	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region) throws ServiceNotFoundException, SQLException {
		return politicalPartyDaoServices.insertUserDetails(
				new User(firstName, lastName, age, emailId, gender, aadharNumber, isAnonymous, region));
	}

	@Override
	public String registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId,
			String gender, String politicianId, Boolean isAnonymous, String region)
			throws ServiceNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return politicalPartyDaoServices.insertPoliticalUserDetails(
				new PoliticalUser(firstName, lastName, emailId, politicianId, gender, age, region, isAnonymous));

	}

	@Override
	public String createGroup(String groupName, String groupDescription, String groupOwnerId) {

		return checkIfUserIsPolitician(groupOwnerId)
				? politicalPartyDaoServices.insertGroupDetails(
						new Group(groupName, groupDescription, groupOwnerId, getCurrentDateOfTypeJavaSql()))
				: "User is not a politician";
	}

	private Boolean checkIfUserIsPolitician(String id) {
		return id.indexOf('P') > -1 ? true : false;
	}

	private Date getCurrentDateOfTypeJavaSql() {
		java.util.Date currentDate = new java.util.Date();
		return new Date(currentDate.getTime());
	}

}
