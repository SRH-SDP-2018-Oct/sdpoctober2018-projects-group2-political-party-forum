package com.politicalforum.providers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.politicalforum.exceptions.ServicNotFoundException;

public class PoliticalPartyConnectionProvider {

	public static Connection getBankingConnectionServices() throws ServicNotFoundException {
		try {
			Properties p = new Properties();
			p.load(new FileReader(new File("resources/politicalparty.properties")));
			Class.forName(p.getProperty("driver"));
			return DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServicNotFoundException("Service down!", e);
		}
	}

}
