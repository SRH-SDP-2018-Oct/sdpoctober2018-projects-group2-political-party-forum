package com.politicalforum.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.exceptions.ServiceNotFoundException;

public interface PoliticalPartyServices {

	public String registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;
	
	public PoliticalUser registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId , Boolean isAnonymous, String region, String password) throws ServiceNotFoundException, SQLException;

	public HashMap<String, Object> login(String emailId, String password) throws SQLException;

	public PoliticalUser createGroup(String groupName, String groupDescription, PoliticalUser politicalUser);
	
	public List<Group> browseGroups() throws SQLException;
	
	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName) throws SQLException;

}
