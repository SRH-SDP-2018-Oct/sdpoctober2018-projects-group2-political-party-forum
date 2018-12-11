package com.politicalforum.tests;

import org.junit.BeforeClass;
import org.junit.Test;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.services.PoliticalPartyServicesImplementation;

public class TestExceptions {

	private static PoliticalPartyServices politicalServices;
	private static User politicalUser;

	@BeforeClass
	public static void setConnectionsBeforeAll() throws ServiceNotFoundException, UserAlreadyExistsException {
		politicalServices = new PoliticalPartyServicesImplementation();
		politicalUser = politicalServices.registerPoliticalUserDetails("pol", "user", 55, "abcd@test.com", "M", "ab12345", true, "delhi", "abcd");
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testIfUserAlreadyExists() throws UserAlreadyExistsException {
		politicalServices.registerPoliticalUserDetails("aaaa", "bbb", 55, "ac@j.com", "m", "a123", false, "aa", "abcd");
	}

	@Test(expected=InvalidCredentialsException.class)
	public void testifInvalidCredentialsException() throws InvalidCredentialsException {
		politicalServices.login("xyzcom", "3mjd.3");
	}
	@Test(expected=GroupAlreadyExistException.class)
	public void testifGroupAlreadyExistException() throws GroupAlreadyExistException {
		politicalServices.createGroup("Project 1", "workflow of current project ", politicalUser);
	}
	
}
