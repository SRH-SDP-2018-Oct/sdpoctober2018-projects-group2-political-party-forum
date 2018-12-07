package com.politicalforum.daoServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.politicalforum.beans.Group;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;
import com.politicalforum.utils.Helper;

public class PoliticalPartyDAOServicesImplementation implements PoliticalPartyDAOServices {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public PoliticalPartyDAOServicesImplementation() throws ServiceNotFoundException {
		
		connection = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
		
	}

	@Override
	public User insertUserDetails(User user) throws SQLException {
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

		return getUserDetails(userId);
	}

	@Override
	public PoliticalUser insertPoliticalUserDetails(PoliticalUser politicalUser) throws SQLException {
		String politicalUserId = null;
		String hashPassword = null;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"insert into userdetails(userid, firstname, lastname, region, emailId, politicalid, gender, age, isanonymous) values('P'||user_sequence.nextval,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, politicalUser.getFirstName());
			preparedStatement.setString(2, politicalUser.getLastName());
			preparedStatement.setString(3, politicalUser.getRegion());
			preparedStatement.setString(4, politicalUser.getEmailId());
			preparedStatement.setString(5, politicalUser.getPoliticianId());
			preparedStatement.setString(6, politicalUser.getGender());
			preparedStatement.setString(7, String.valueOf(politicalUser.getAge()));
			preparedStatement.setInt(8, politicalUser.getIsAnonymous() ? 1 : 0);
			preparedStatement.executeUpdate();
			preparedStatement = connection
					.prepareStatement("select max(userid) from userdetails where userid like 'P%'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			politicalUserId = resultSet.getString(1);
			System.out.println("Political Id:- " + politicalUserId);
			hashPassword = Helper.generateHashPassword(politicalUser.getPassword());
			preparedStatement = connection
					.prepareStatement("insert into usercredentials(userid, hashpassword) values(?,?)");
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
		return getPoliticalUserDetails(politicalUserId);
	}

	@Override
	public List<Group> checkIfGroupWithSimilarNameExists(String groupName) throws SQLException {
		List<Group> groups = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(
					"select UPPER(groupdetailsname) from groupdetails where groupdetailsname like '%" + groupName + "%'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(new Group(resultSet.getString(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
		}
		return groups;
	}

	@Override
	public Group insertGroupDetails(Group group) {
		String groupId = null;
		try {
			preparedStatement = connection.prepareStatement(
					"insert into groupdetails(groupdetailsid, groupdetailsname, groupdetailsbody, userid, dateofcreation) values('GD' || groupdetail_sequence.nextval,?,?,?,?)");
			preparedStatement.setString(1, group.getGroupName());
			preparedStatement.setString(2, group.getGroupDescription());
			preparedStatement.setString(3, group.getGroupOwnerId());
			preparedStatement.setDate(4, group.getGroupCreationTime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("select max(groupdetailsId) from Groupdetails");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				groupId = resultSet.getString(1);
			preparedStatement.close();
			resultSet.close();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Group(groupId, group.getGroupName(), group.getGroupDescription(), group.getGroupOwnerId(),
				group.getGroupCreationTime());
	}

	@Override
	public HashMap<String, Object> getUser(String emailId, String password) throws SQLException {
		try {
			connection.setAutoCommit(false);
			String userId = getUserId(emailId);
			System.out.println("User- " + userId);
			Boolean isPasswordValid = userId != null ? checkCredentialsForUser(userId, password) : false;
			if (isPasswordValid) {
				return identifyAndCreateUser(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
		}
		return null;
	}
	
	@Override
	public Boolean addFollowerToAGroup(String userId, Group group) {
		try {
			preparedStatement = connection.prepareStatement("insert into groupfollowers(groupfollowersid, groupdetailsid, userid) values('GF'||groupfollowers_sequence.nextval,?,?)");
			preparedStatement.setString(1, group.getGroupId());
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
	
	private HashMap<String, Object> identifyAndCreateUser(String userId) throws SQLException {
		Boolean isUserPolitician = Helper.checkIfUserIsPolitician(userId);
		HashMap<String, Object> map = new HashMap<>();
		if (isUserPolitician) {
			map.put(userId, getPoliticalUserDetails(userId));
		} else {
			map.put(userId, getUserDetails(userId));
		}
		return map;
	}

	private String getUserId(String emailId) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select userid from userdetails where emailid='" + emailId + "'");
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString(1);
		}
		return null;
	}

	private User getUserDetails(String userId) throws SQLException {
		preparedStatement = connection.prepareStatement("select * from userdetails where userid='" + userId + "'");
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String region = resultSet.getString(4);
			String emailId = resultSet.getString(5);
			String aadharNumber = resultSet.getString(6);
			String gender = resultSet.getString(7);
			int age = Integer.parseInt(resultSet.getString(8));
			Boolean isAnonymous = resultSet.getInt(9) > 0 ? true : false;
			return new User(userId, firstName, lastName, age, emailId, gender, aadharNumber, isAnonymous, region);
		}
		return null;
	}

	private List<Group> getGroupDetails(String politicalUserId) {
		List<Group> groups = new ArrayList<>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from groupdetails where userid='" + politicalUserId + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				groups.add(new Group(resultSet.getString("groupdetailsid"), resultSet.getString("groupdetailsname"),
						resultSet.getString("groupdetailsbody"), resultSet.getString("userid"),
						resultSet.getDate("dateofcreation")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}

	private PoliticalUser getPoliticalUserDetails(String politicalUserId) throws SQLException {
		preparedStatement = connection
				.prepareStatement("select * from userdetails where userid='" + politicalUserId + "'");
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String region = resultSet.getString(4);
			String emailId = resultSet.getString(5);
			String politicianId = resultSet.getString(6);
			String gender = resultSet.getString(7);
			int age = Integer.parseInt(resultSet.getString(8));
			Boolean isAnonymous = resultSet.getInt(9) > 0 ? true : false;
			return new PoliticalUser(politicalUserId, firstName, lastName, emailId, politicianId, gender, age, region,
					isAnonymous, getGroupDetails(politicalUserId));
		}
		return null;
	}

	@Override
	public List<Group> retrieveGroupDetails() throws SQLException {
		preparedStatement = connection.prepareStatement("select * from groupdetails");
		resultSet = preparedStatement.executeQuery();
		List<Group> group = new ArrayList<>();
		while (resultSet.next()) {
			String groupId = resultSet.getString(1);
			String groupName = resultSet.getString(2);
			String groupDescription = resultSet.getString(3);
			String groupOwnerId = resultSet.getString(4);
			Date groupCreationTime = resultSet.getDate(5);
			group.add(new Group(groupId, groupName, groupDescription, groupOwnerId, groupCreationTime));
		}
		return group;
	}

	private Boolean checkCredentialsForUser(String id, String password) throws SQLException {
		resultSet = connection.prepareStatement("select hashpassword from usercredentials where userid='" + id + "'")
				.executeQuery();
		if (resultSet.next()) {
			return Helper.isPasswordCorrect(password, resultSet.getString(1));
		}
		return false;
	}

}
