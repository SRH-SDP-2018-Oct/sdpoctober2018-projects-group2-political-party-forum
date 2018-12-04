package com.politicalforum.providers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.services.PoliticalPartyServices;

public class PoliticalPartyServicesProvider {

	public static PoliticalPartyServices getPoliticalPartyServiceImplementor() throws ServiceNotFoundException {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File("resources/politicalparty.properties")));
			return (PoliticalPartyServices) Class.forName(prop.getProperty("politicalPartyServices")).getConstructor()
					.newInstance();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			throw new ServiceNotFoundException("Service down!", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceNotFoundException("Service down!", e);
		}
	}
}
