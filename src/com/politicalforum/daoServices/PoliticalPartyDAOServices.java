package com.politicalforum.daoServices;

import java.sql.SQLException;

import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;

public interface PoliticalPartyDAOServices {
	public String insertUserDetails(User user) throws SQLException;
	public String insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException;
}
