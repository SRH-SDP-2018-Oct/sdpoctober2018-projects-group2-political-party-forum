package com.politicalforum.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.politicalforum.beans.GeneralUser;
import com.politicalforum.beans.Group;
import com.politicalforum.beans.GroupDiscussion;
import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.daoServices.PoliticalPartyDAOServices;
import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyDAOServicesProvider;
import com.politicalforum.utils.Helper;

public class PoliticalPartyServicesImplementation implements PoliticalPartyServices {
	PoliticalPartyDAOServices politicalPartyDaoServices;

	public PoliticalPartyServicesImplementation() throws ServiceNotFoundException {
		politicalPartyDaoServices = PoliticalPartyDAOServicesProvider.getPoliticalPartyDAOServicesImplementor();
	}

	@Override
	public User registerUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String aadharNumber, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException {
		return politicalPartyDaoServices.insertUserDetails(new GeneralUser(firstName, lastName, age, emailId, gender,
				isAnonymous, region, password, new ArrayList<>(), aadharNumber));
	}

	@Override
	public User registerPoliticalUserDetails(String firstName, String lastName, int age, String emailId, String gender,
			String politicianId, Boolean isAnonymous, String region, String password)
			throws ServiceNotFoundException, SQLException {
		return politicalPartyDaoServices.insertPoliticalUserDetails(new PoliticalUser(firstName, lastName, age, emailId,
				gender, isAnonymous, region, password, new ArrayList<>(), politicianId));
	}

	@Override
	public List<Group> checkIfGroupExistsWithSimilarNames(String groupName) throws SQLException {
		return politicalPartyDaoServices.checkIfGroupWithSimilarNameExists(groupName);
	}

	@Override
	public User createGroup(String groupName, String groupDescription, User user) {
		if (Helper.checkIfUserIsPolitician(user.getUserId())) {
			Group group = politicalPartyDaoServices.insertGroupDetails(
					new Group(groupName, groupDescription, user.getUserId(), Helper.getCurrentDateOfTypeJavaSql()));
			
			if (politicalPartyDaoServices.addFollowerToAGroup(user.getUserId(), group)) {
				user.getGroups().add(group);
				return user;
			}
		}
		return null; // Throw Error
	}

	@Override
	public User login(String emailId, String password) throws SQLException {
		User user = politicalPartyDaoServices.getUser(emailId, password);
		user.setGroups(politicalPartyDaoServices.getUserGroups(user.getUserId()));
		return user;
	}

	@Override
	public List<Group> browseGroups() throws SQLException {
		return politicalPartyDaoServices.retrieveGroupDetails();
	}

	@Override
	public User joinGroup(User user, Group group) {
		if (politicalPartyDaoServices.addFollowerToAGroup(user.getUserId(), group)) {
			user.getGroups().add(group);
			return user;
		}
		return null;
	}

	@Override
	public User createDiscussion(User user, String groupDiscussionName, String groupDiscussionBody) {
		user.setSelectedGroup(politicalPartyDaoServices.createDiscussion(user.getUserId(), user.getSelectedGroup(),
				new GroupDiscussion(groupDiscussionName, groupDiscussionBody, Helper.getCurrentDateOfTypeJavaSql())));
		return user;
	}

	@Override
	public List<GroupDiscussion> viewAllDiscussions(String groupId) {
		return politicalPartyDaoServices.fetchAllDiscussions(groupId);
	}

}
