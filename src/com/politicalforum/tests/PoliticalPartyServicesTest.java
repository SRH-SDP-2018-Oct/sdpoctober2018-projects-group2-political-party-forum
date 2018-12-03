package com.politicalforum.tests;

import org.hamcrest.core.StringStartsWith;
import org.junit.BeforeClass;
import org.junit.Test;

import com.politicalforum.exceptions.ServicNotFoundException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.services.PoliticalPartyServicesImplementation;

import static org.hamcrest.MatcherAssert.assertThat;


public class PoliticalPartyServicesTest {

	private static PoliticalPartyServices politicalServices;
	
	@BeforeClass
	public static void setConnectionsBeforeAll() throws ServicNotFoundException {
		politicalServices = new PoliticalPartyServicesImplementation();
	}
	
	
	@Test
	public void testRegisterUserDetails() throws ServicNotFoundException {
		assertThat(politicalServices.registerUserDetails("First", "Test", 30, "email2@id.com", "Female", "34124", false, "Delhi") , StringStartsWith.startsWith("U"));
	}

}
