package com.politicalforum.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.exceptions.ServiceNotFoundException;

public interface PoliticalPartyServices {

	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;
	
	public String registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId , Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;

	public HashMap<String, Object> login(String emailId, String password) throws SQLException;

	public String createGroup(String groupName, String groupDescription, String groupOwnerId);
	
	public List<Group> browseGroups() throws SQLException;

}
