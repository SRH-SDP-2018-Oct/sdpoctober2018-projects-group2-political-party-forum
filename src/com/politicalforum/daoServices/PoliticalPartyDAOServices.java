package com.politicalforum.daoServices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;

public interface PoliticalPartyDAOServices {
	public User insertUserDetails(User user) throws SQLException;

	public PoliticalUser insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException;

	public List<Group> checkIfGroupWithSimilarNameExists(String groupName) throws SQLException;

	public Group insertGroupDetails(Group group);

	public HashMap<String, Object> getUser(String emailId, String password) throws SQLException;

	public List<Group> retrieveGroupDetails() throws SQLException;
}
