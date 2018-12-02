package com.politicalforum.providers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.politicalforum.exceptions.ServicNotFoundException;
import com.politicalforum.services.PoliticalPartyServices;

public class PoliticalPartyServicesProvider {

	public static PoliticalPartyServices getPoliticalPartyServiceImplementor() throws ServicNotFoundException {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File("resources/politicalparty.properties")));
			return (PoliticalPartyServices) Class.forName(prop.getProperty("politicalPartyServices")).getConstructor()
					.newInstance();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		}
	}
}
