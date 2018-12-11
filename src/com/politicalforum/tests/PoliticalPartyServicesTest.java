package com.politicalforum.tests;

import org.hamcrest.core.StringStartsWith;
import org.junit.BeforeClass;
import org.junit.Test;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.GroupAlreadyExistException;
import com.politicalforum.exceptions.InvalidCredentialsException;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.exceptions.UserAlreadyExistsException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.services.PoliticalPartyServicesImplementation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;


public class PoliticalPartyServicesTest {

	private static PoliticalPartyServices politicalServices;
	private static User generalUser, politicalUser;
	@BeforeClass
	public static void setConnectionsBeforeAll() throws ServiceNotFoundException {
		politicalServices = new PoliticalPartyServicesImplementation();
	}
	
	@Test
	public void testRegisterUserDetails() throws ServiceNotFoundException, UserAlreadyExistsException {
		generalUser = politicalServices.registerUserDetails("asd", "cc", 25, "ak1@ada", "M", "17723", false, "aaa", "abc");
		assertThat(generalUser.getUserId(), StringStartsWith.startsWith("U"));
	}
    @Test
    public void testRegisterPoliticalUserDetails() throws ServiceNotFoundException, UserAlreadyExistsException {
    	politicalUser = politicalServices.registerPoliticalUserDetails("mm", "bal", 25, "ac@hj", "M", "1245", false, "kol", "qweruz");
    	assertThat(politicalUser.getUserId(), StringStartsWith.startsWith("P"));
    }
    @Test
    public void testlogin() throws InvalidCredentialsException {
    	assertThat(politicalServices.login("ak1@ada", "abc").getUserId(), StringStartsWith.startsWith("U")); 
    }
    @Test
    public void testcreateGroup() throws GroupAlreadyExistException {
    	
    	assertNull(politicalServices.createGroup("bjp", "the party", generalUser));
        assertFalse(politicalServices.createGroup("bjp", "the party", politicalUser).getGroups().isEmpty());
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

