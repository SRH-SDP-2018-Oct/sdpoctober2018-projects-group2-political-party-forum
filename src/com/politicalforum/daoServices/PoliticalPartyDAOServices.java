package com.politicalforum.daoServices;

import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;

public interface PoliticalPartyDAOServices {
	public String insertUserDetails(User user);
	public String insertPoliticalUserDetails(PoliticalUser politicalUser);
}
