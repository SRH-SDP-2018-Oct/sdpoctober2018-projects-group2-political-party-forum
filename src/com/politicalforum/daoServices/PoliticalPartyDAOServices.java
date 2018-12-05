package com.politicalforum.daoServices;

import java.sql.SQLException;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.utils.GenericUser;

public interface PoliticalPartyDAOServices {
	public String insertUserDetails(User user) throws SQLException;
	public String insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException;
	public String insertGroupDetails(Group group);
	public <T> GenericUser<T> checkCredentials(String emailId, String password) throws SQLException;
	public List<Group> retrieveGroupDetails() throws SQLException;
}
