package com.politicalforum.services;

import java.sql.SQLException;

import com.politicalforum.exceptions.ServiceNotFoundException;

public interface PoliticalPartyServices {

	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;
	
	public String registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId , Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;

	public Boolean login(String emailId, String password) throws SQLException;

}

