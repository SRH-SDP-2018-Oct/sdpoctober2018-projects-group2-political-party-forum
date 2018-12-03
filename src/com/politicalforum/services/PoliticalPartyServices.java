package com.politicalforum.services;

import com.politicalforum.exceptions.ServicNotFoundException;

public interface PoliticalPartyServices {

	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region) throws ServicNotFoundException;
	
	public String registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId , Boolean isAnonymous, String region) throws ServicNotFoundException;

}

