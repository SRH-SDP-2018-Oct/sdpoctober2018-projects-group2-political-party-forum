package com.politicalforum.daoServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;
import com.politicalforum.utils.GenericUser;
import com.politicalforum.utils.Helper;

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
		String hashPassword = null;
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
			hashPassword = Helper.generateHashPassword(user.getPassword());
			preparedStatement = connection
					.prepareStatement("insert into usercredentials(userid, hashpassword) values(?,?)");
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, hashPassword);
			preparedStatement.executeUpdate();
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
		String hashPassword = null;
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
			hashPassword = Helper.generateHashPassword(politicalUser.getPassword());
			preparedStatement = connection.prepareStatement(
					"insert into politicalusercredentials(politicaluserid, hashpassword) values(?,?)");
			preparedStatement.setString(1, politicalUserId);
			preparedStatement.setString(2, hashPassword);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return politicalUserId;
	}

	@Override
	public String insertGroupDetails(Group group) {
		String groupId = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into groupdetails(groupdetailsid, groupdetailsname, groupdetailsbody, politicaluserid, dateofcreation) values('GD' || groupdetail_sequence.nextval,?,?,?,?)");
			preparedStatement.setString(1, group.getGroupName());
			preparedStatement.setString(2, group.getGroupDescription());
			preparedStatement.setString(3, group.getGroupOwnerId());
			preparedStatement.setDate(4, group.getGroupCreationTime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("select max(groupdetailsId) from Groupdetails");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				groupId = resultSet.getString(1);
			preparedStatement.close();
			resultSet.close();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupId;
	}
	
	@Override
	public <T> GenericUser<T> checkCredentials(String emailId, String password) throws SQLException {
		try {
			connection.setAutoCommit(false);
			return checkUser(emailId, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
		}
		return null;
	}

	private User getUserDetails(String userId) throws SQLException {
		preparedStatement = connection.prepareStatement("select * from userdetails where userid='"+userId+"'");
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String region = resultSet.getString(4);
			String emailId = resultSet.getString(5);
			String aadharNumber = resultSet.getString(6);
			String gender = resultSet.getString(7);
			int age = Integer.parseInt(resultSet.getString(8));
			Boolean isAnonymous = resultSet.getInt(9) > 0? true:false;
			return new User(userId, firstName, lastName, age, emailId, gender, aadharNumber, isAnonymous, region);
		}
		return null;
	}
	
	private PoliticalUser getPoliticalUserDetails(String politicalUserId) throws SQLException {
		preparedStatement = connection.prepareStatement("select * from politicaluserdetails where politicaluserid='"+politicalUserId+"'");
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String region = resultSet.getString(4);
			String emailId = resultSet.getString(5);
			String politicianId = resultSet.getString(6);
			String gender = resultSet.getString(7);
			int age = Integer.parseInt(resultSet.getString(8));
			Boolean isAnonymous = resultSet.getInt(9) > 0? true:false;
			return new PoliticalUser(politicalUserId, firstName, lastName, emailId, politicianId, gender, age, region, isAnonymous);
		}
		return null;
	}
	
	
	private <T> GenericUser<T> checkUser(String emailId, String password) throws SQLException {
		resultSet = connection.prepareStatement("select userid from userdetails where emailid='" + emailId + "'")
				.executeQuery();
		String id = null;
		if (resultSet.next()) {
			 id = resultSet.getString(1);
			return checkCredentialsForUser(id, password)? new GenericUser<T>(getUserDetails(id)) : null;
		} else {
			resultSet = connection
					.prepareStatement(
							"select politicaluserid from politicaluserdetails where emailid='" + emailId + "'")
					.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getString(1);
				return checkCredentialsForPoliticalUser(id, password)? new GenericUser<T>(getPoliticalUserDetails(id)) : null;
			}
			return null;
		}
	}


	private Boolean checkCredentialsForUser(String id, String password) throws SQLException {
		resultSet = connection.prepareStatement("select hashpassword from usercredentials where userid='" + id + "'")
				.executeQuery();
		if (resultSet.next()) {
			return Helper.isPasswordCorrect(password, resultSet.getString(1));
		}
		return false;
	}

	private Boolean checkCredentialsForPoliticalUser(String id, String password) throws SQLException {
		resultSet = connection
				.prepareStatement(
						"select hashpassword from politicalusercredentials where politicaluserid='" + id + "'")
				.executeQuery();
		if (resultSet.next()) {
			return Helper.isPasswordCorrect(password, resultSet.getString(1));
		}
		return false;
	}

}
