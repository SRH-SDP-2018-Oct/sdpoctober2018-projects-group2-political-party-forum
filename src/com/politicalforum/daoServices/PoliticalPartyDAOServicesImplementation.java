package com.politicalforum.daoServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.politicalforum.beans.User;
import com.politicalforum.exceptions.ServicNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;

public class PoliticalPartyDAOServicesImplementation implements PoliticalPartyDAOServices {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public PoliticalPartyDAOServicesImplementation() throws ServicNotFoundException {
		connection = PoliticalPartyConnectionProvider.getBankingConnectionServices();
	}

	@Override
	public String insertUserDetails(User user) {
		String userId = null;
		try {
			connection.setAutoCommit(false);
			String sql = "select user_sequence.nextval from userdetails";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				userId = "U"+resultSet.getString(1);
				preparedStatement.closeOnCompletion();
			}
			preparedStatement = connection.prepareStatement(
					"insert into userdetails(userid, firstname, lastname, region, emailId, aadharnumber, gender, age, isanonymous) values(?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRegion());
			preparedStatement.setString(5, user.getEmailId());
			preparedStatement.setString(6, user.getAadharNumber());
			preparedStatement.setString(7, user.getGender());
			preparedStatement.setString(8, String.valueOf(user.getAge()));
			preparedStatement.setInt(9, user.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("select max(userid) from userdetails");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			userId = resultSet.getString(1);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userId;
	}

}
