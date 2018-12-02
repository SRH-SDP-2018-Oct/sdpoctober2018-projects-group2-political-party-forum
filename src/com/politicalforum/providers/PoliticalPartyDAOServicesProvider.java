package com.politicalforum.providers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.ServicNotFoundException;

public class PoliticalPartyDAOServicesProvider {

	public static PoliticalPartyDAOServices getPoliticalPartyDAOServicesImplementor() throws ServicNotFoundException {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File("resources/politicalparty.properties")));
			return (PoliticalPartyDAOServices) Class.forName(prop.getProperty("politicalPartyDAOServices"))
					.getConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service Down!!", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service Down", e);
		}
	}
}
