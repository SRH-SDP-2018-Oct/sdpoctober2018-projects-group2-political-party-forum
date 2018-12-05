package com.politicalforum.services;

import java.sql.SQLException;

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
	public Boolean login(String emailId, String password) throws SQLException {
		return politicalPartyDaoServices.checkCredentials(emailId, password);
	}

}
