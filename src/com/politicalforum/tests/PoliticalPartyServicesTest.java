package com.politicalforum.tests;

import org.hamcrest.core.StringStartsWith;
import org.junit.BeforeClass;
import org.junit.Test;

import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.services.PoliticalPartyServicesImplementation;

import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;


public class PoliticalPartyServicesTest {

	private static PoliticalPartyServices politicalServices;
	
	@BeforeClass
	public static void setConnectionsBeforeAll() throws ServiceNotFoundException {
		politicalServices = new PoliticalPartyServicesImplementation();
	}
		
	@Test
	public void testRegisterUserDetails() throws ServiceNotFoundException, SQLException {
	//	assertThat(politicalServices.registerUserDetails("gfhgfh", "sshjg", 55, "abcdeed@efg.com", "Male", "dgghjsdaghjje768e7d", true, "hsdjdg", "abcd") , StringStartsWith.startsWith("U"));
	}

}
