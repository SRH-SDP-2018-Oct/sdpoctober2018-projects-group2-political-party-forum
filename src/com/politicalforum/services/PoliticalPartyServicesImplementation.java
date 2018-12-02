package com.politicalforum.services;

import com.politicalforum.beans.User;
import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.ServicNotFoundException;
import com.politicalforum.providers.PoliticalPartyDAOServicesProvider;

public class PoliticalPartyServicesImplementation implements PoliticalPartyServices {
	PoliticalPartyDAOServices politicalPartyDaoServices;

	public PoliticalPartyServicesImplementation() throws ServicNotFoundException {
		politicalPartyDaoServices = PoliticalPartyDAOServicesProvider.getPoliticalPartyDAOServicesImplementor();
	}

	@Override
	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region) throws ServicNotFoundException {
		return politicalPartyDaoServices.insertUserDetails(
				new User(firstName, lastName, age, emailId, gender, aadharNumber, isAnonymous, region));
	}

}
