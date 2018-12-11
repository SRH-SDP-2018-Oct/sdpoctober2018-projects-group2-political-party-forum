package com.politicalforum.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertFalse;

import org.hamcrest.core.StringStartsWith;
import org.junit.BeforeClass;
import org.junit.Test;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.services.PoliticalPartyServicesImplementation;

public class userServicestest {
	private static PoliticalPartyServices politicalServices;
	private static User generalUser;

	@BeforeClass
	public static void setConnectionsBeforeAll() throws ServiceNotFoundException {
		politicalServices = new PoliticalPartyServicesImplementation();

	}

	@Test 
	public void testUserRegisterUserDetails()
			throws ServiceNotFoundException, SQLException, UserAlreadyExistsException {
		generalUser = politicalServices.registerUserDetails("Rahul", "Sonawane", 25, "rahul@gmail.com", "male",
				"123456789123", true, "Dhule", "abcd");
		assertThat(generalUser.getUserId(), StringStartsWith.startsWith("U"));
	}

	@Test
	public void testregisterPoliticalUserDetails() throws ServiceNotFoundException,SQLException, UserAlreadyExistsException{
		assertThat(politicalServices.registerPoliticalUserDetails("mubeen", "mirza", 23, "mubi@gmail.com", "male", "12345", true , "punjab", "34567").getUserId(), StringStartsWith.startsWith("p"));
		
	}
	@Test
	public void testUserLogin() throws SQLException, InvalidCredentialsException {
	assertThat(politicalServices.login("been@gmail.com", "1238"), instanceOf((User.class)));
	}
	@Test

	public void testbrowseGroup() {
		assertFalse(politicalServices.browseGroups().isEmpty());
	}

	@Test
	public void testcheckIfGroupExistsWithSimilarNames() {
		assertFalse(politicalServices.checkIfGroupExistsWithSimilarNames("bjp").isEmpty());
	}
	
}
