package com.politicalforum.daoServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;

public class PoliticalPartyDAOServicesImplementation implements PoliticalPartyDAOServices {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public PoliticalPartyDAOServicesImplementation() throws ServiceNotFoundException {
		connection = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
	}

	@Override
	public String insertUserDetails(User user) throws SQLException {
		String userId = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into userdetails(userid, firstname, lastname, region, emailId, aadharnumber, gender, age, isanonymous) values('U'||user_sequence.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getRegion());
			preparedStatement.setString(4, user.getEmailId());
			preparedStatement.setString(5, user.getAadharNumber());
			preparedStatement.setString(6, user.getGender());
			preparedStatement.setString(7, String.valueOf(user.getAge()));
			preparedStatement.setInt(8, user.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("select max(userid) from userdetails");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			userId = resultSet.getString(1);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}

		return userId;
	}

	@Override
	public String insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException {
		String politicalUserId = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into politicaluserdetails(politicaluserid, firstname, lastname, region, emailId, politicianId, gender, age, isanonymous) values('P'||politicaluser_sequence.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, politicalUser.getFirstName());
			preparedStatement.setString(2, politicalUser.getLastName());
			preparedStatement.setString(3, politicalUser.getRegion());
			preparedStatement.setString(4, politicalUser.getEmailId());
			preparedStatement.setString(5, politicalUser.getPoliticianId());
			preparedStatement.setString(6, politicalUser.getGender());
			preparedStatement.setString(7, String.valueOf(politicalUser.getAge()));
			preparedStatement.setInt(8, politicalUser.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("select max(POLITICALUSERID) from politicaluserdetails");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			politicalUserId = resultSet.getString(1);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}


		return politicalUserId;
	}
	
	

}
