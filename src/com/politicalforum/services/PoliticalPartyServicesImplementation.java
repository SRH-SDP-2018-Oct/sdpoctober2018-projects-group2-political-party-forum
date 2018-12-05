package com.politicalforum.services;

import java.sql.SQLException;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyDAOServicesProvider;
import com.politicalforum.utils.GenericUser;
import com.politicalforum.utils.Helper;

public class PoliticalPartyServicesImplementation implements PoliticalPartyServices {
	PoliticalPartyDAOServices politicalPartyDaoServices;

	public PoliticalPartyServicesImplementation() throws ServiceNotFoundException {
		politicalPartyDaoServices = PoliticalPartyDAOServicesProvider.getPoliticalPartyDAOServicesImplementor();
	}

	@Override
	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException {
		return politicalPartyDaoServices.insertUserDetails(
				new User(firstName, lastName, age, emailId, gender, aadharNumber, isAnonymous, region, password));
	}

	@Override
	public String registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId,
			String gender, String politicianId, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return politicalPartyDaoServices.insertPoliticalUserDetails(
				new PoliticalUser(firstName, lastName, emailId, politicianId, gender, age, region, isAnonymous, password));
	}

	@Override
	public String createGroup(String groupName, String groupDescription, String groupOwnerId) {

		return Helper.checkIfUserIsPolitician(groupOwnerId)
				? politicalPartyDaoServices.insertGroupDetails(
						new Group(groupName, groupDescription, groupOwnerId, Helper.getCurrentDateOfTypeJavaSql()))
				: "User is not a politician";
	}
	
	public <T> GenericUser<T> login(String emailId, String password) throws SQLException {
		return politicalPartyDaoServices.checkCredentials(emailId, password);
		
	}

}
